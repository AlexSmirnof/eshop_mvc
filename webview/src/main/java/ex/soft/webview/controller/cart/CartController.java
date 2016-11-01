package ex.soft.webview.controller.cart;

import ex.soft.domain.model.Cart;
import ex.soft.domain.model.Phone;
import ex.soft.service.CartService;
import ex.soft.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * Created by Alex108 on 19.10.2016.
 */
@RequestMapping("/cart")
@Controller
@SessionAttributes("cart")
public class CartController {

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private CartService cartService;

    @ModelAttribute("cart")
    public Cart showCartWidget(HttpSession session) {
        System.out.println("show cart widget");
        return cartService.getCart(session);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showCartPage(){
        return "cart/cart";
    }

    @RequestMapping(value = "/addProductToCart/{key}", method = RequestMethod.POST)
    public @ResponseBody String addProductToCart(@Valid @Size(min = 1) @ModelAttribute("quantity") Long quantity, BindingResult result,
                                                 @PathVariable("key") Long productId,
                                                 HttpSession session ){
        System.out.println("AJAX: key="+ productId + " quan=" + quantity);
        if (result.hasErrors()){
            System.out.println("ERROR " + result.toString());
            return "Error: Invalid quantity number";
        } else if (quantity <= 0){
            return "Error: Quantity must be greater than 0";
        } else {
            Phone phone = phoneService.getProduct(productId);
            cartService.addProductToCart(session, phone, quantity);
            return phone.getModel() + " in " + quantity + " items, added to cart";
        }
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
            cartService.deleteProductFromCart(session, phone, quantity);
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

}
