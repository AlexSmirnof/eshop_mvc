package ex.soft.webview.controller.productList;


import ex.soft.domain.model.Cart;
import ex.soft.domain.model.OrderItem;
import ex.soft.domain.model.Phone;
import ex.soft.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Alex108 on 12.10.2016.
 */
@RequestMapping("/productList")
@Controller
@SessionAttributes("cart")
public class ProductListController {

    @Autowired
    @Qualifier("phoneService")
    private PhoneService phoneService;

    @ModelAttribute("cart")
    public Cart showCartWidget(HttpSession session) {
        System.out.println("ModelAttribute");
        Cart cart = (Cart) session.getAttribute("cart");
        return cart != null ? cart : new Cart();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showProductList(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("productList/productList");
        List<Phone> productList = phoneService.listProducts();
        modelAndView.addObject("productList", productList);
        System.out.println("PRODUCTS: " + productList);
        return modelAndView;
    }

    @RequestMapping(value = "/addProductToCart/{key}", method = RequestMethod.POST)
    public @ResponseBody String addProductToCart(@Valid @Size(min = 1) @ModelAttribute("quantity") Long quantity, BindingResult result,
                                                 @PathVariable("key") Long productId,
                                                 HttpSession session){
        System.out.println("AJAX: key="+ productId + " quan=" + quantity);
        if (result.hasErrors()){
            System.out.println("ERROR " + result.toString());
            return "Error: Invalid quantity number";
        } else if (quantity <= 0){
            return "Error: Quantity must be greater than 0";
        } else {
            Phone phone = phoneService.getProduct(productId);
            Cart cart = (Cart) session.getAttribute("cart");

            int index = findIndexOfProductInCart(cart, phone);
            if( index < 0 ){
                cart.getOrderItems().add(new OrderItem(phone, quantity));
            } else {
                Long oldQuantity = cart.getOrderItems().get(index).getQuantity();
                cart.getOrderItems().get(index).setQuantity(Long.sum(oldQuantity, quantity));
            }

            Long totalQuantity = Long.sum(cart.getTotalQuantity(), quantity);
            cart.setTotalQuantity(totalQuantity);

            BigDecimal totalPrice = phone.getPrice().multiply(BigDecimal.valueOf(quantity.longValue()));
            totalPrice = cart.getTotalPrice().add(totalPrice);
            cart.setTotalPrice(totalPrice);

            session.setAttribute("cart", cart);

            return phone.getModel() + " in " + quantity + " items, added to cart";
        }
    }

    public static int findIndexOfProductInCart(Cart cart, Phone phone){
        List<OrderItem> orderItems = cart.getOrderItems();
        int index = 0;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getPhone().equals(phone)) {
                return index;
            }
            index++;
        }
        return -1;
    }
}
