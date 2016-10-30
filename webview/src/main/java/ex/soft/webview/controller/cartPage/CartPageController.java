package ex.soft.webview.controller.cartPage;

import ex.soft.domain.model.Cart;
import ex.soft.domain.model.OrderItem;
import ex.soft.domain.model.Phone;
import ex.soft.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Alex108 on 19.10.2016.
 */
@RequestMapping("/cartPage")
@Controller
@SessionAttributes("cart")
public class CartPageController {

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
    public String showCartPage(){
        return "cartPage/cartPage";
    }

    @RequestMapping(value = "deleteProductFromCart/{key}", method = RequestMethod.POST)
    public @ResponseBody String deleteProductFromCart(@Valid @Size(min = 1) @ModelAttribute("quantity") Long quantity, BindingResult result,
                                                      @PathVariable("key") Long productId,
                                                      HttpSession session){
        System.out.println("DELETE: key="+ productId + " quan=" + quantity);
        if (result.hasErrors()){
            System.out.println("ERROR " + result.toString());
            return "Error: Invalid quantity number";
        } else if (quantity <= 0){
            return "Error: Quantity must be greater than 0";
        } else {
            Phone phone = phoneService.getProduct(productId);

            Cart cart = (Cart) session.getAttribute("cart");
            int index = findIndexOfProductInCart(cart, phone);
            if ( index < 0 ){
                return "Error: Product with key=" + productId + " does`t exist in cart";
            } else {
                Long oldQuantity = cart.getOrderItems().get(index).getQuantity();
                if ( oldQuantity > quantity ){
                    cart.getOrderItems().get(index).setQuantity(Long.sum(oldQuantity, -quantity));

                    Long totalQuantity = Long.sum(cart.getTotalQuantity(), -quantity);
                    cart.setTotalQuantity(totalQuantity);

                    BigDecimal totalPrice = phone.getPrice().multiply(BigDecimal.valueOf(quantity.longValue()));
                    totalPrice = cart.getTotalPrice().subtract(totalPrice);
                    cart.setTotalPrice(totalPrice);
                } else {
                    cart.getOrderItems().remove(index);
                    Long totalQuantity = Long.sum(cart.getTotalQuantity(), -oldQuantity);
                    cart.setTotalQuantity(totalQuantity);

                    BigDecimal totalPrice = phone.getPrice().multiply(BigDecimal.valueOf(oldQuantity.longValue()));
                    totalPrice = cart.getTotalPrice().subtract(totalPrice);
                    cart.setTotalPrice(totalPrice);
                }
            }
            session.setAttribute("cart", cart);
            return phone.getModel() + " in " + quantity + " items, deleted from cart";
        }
    }

    @RequestMapping(value = "/getCartJson", method = RequestMethod.GET /*produces = "application/json"*/)
    public @ResponseBody String getCartJson(HttpSession session){
        System.out.println("GET Json");
        Cart cart = (Cart) session.getAttribute("cart");
        System.out.println(cart);
        cart = cart == null ? new Cart() : cart;
        String data = String.format("{\"quantity\":%d, \"price\":%s}", cart.getTotalQuantity(), cart.getTotalPrice());
        System.out.println(data);
        return data;
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
