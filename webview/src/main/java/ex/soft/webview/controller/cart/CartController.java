package ex.soft.webview.controller.cart;

import ex.soft.domain.model.Cart;
import ex.soft.domain.model.Phone;
import ex.soft.service.CartService;
import ex.soft.service.PhoneService;
import org.apache.log4j.Logger;
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

    private static final Logger LOGGER = Logger.getLogger(CartController.class);

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private CartService cartService;

    @ModelAttribute("cart")
    public Cart showCartWidget(HttpSession session) {
        LOGGER.info("Show Cart Widget");
        System.out.println("Show Widget");
        System.out.println(LOGGER);
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
        LOGGER.info("ADD: key="+ productId + " quan=" + quantity);
        if (result.hasErrors()){
            LOGGER.error(result.toString());
            return "Error: Invalid quantity number";
        } else if (quantity <= 0){
            LOGGER.error("Quantity must be greater than 0");
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
        LOGGER.info("DELETE: key="+ productId + " quan=" + quantity);
        if (result.hasErrors()){
            LOGGER.error(result.toString());
            return "Error: Invalid quantity number";
        } else if (quantity <= 0){
            LOGGER.error("Quantity must be greater than 0");
            return "Error: Quantity must be greater than 0";
        } else {
            Phone phone = phoneService.getProduct(productId);
            cartService.deleteProductFromCart(session, phone, quantity);
            return phone.getModel() + " in " + quantity + " items, deleted from cart";
        }
    }

    @RequestMapping(value = "/getCartJson", method = RequestMethod.GET)
    public @ResponseBody String getCartJson(HttpSession session){
        LOGGER.info("AJAX: Get Json");
        Cart cart = (Cart) session.getAttribute("cart");
        LOGGER.info(cart);
        cart = cart == null ? new Cart() : cart;
        String data = String.format("{\"quantity\":%d, \"price\":%s}", cart.getTotalQuantity(), cart.getTotalPrice());
        LOGGER.info("Data: " + data);
        return data;
    }

}
