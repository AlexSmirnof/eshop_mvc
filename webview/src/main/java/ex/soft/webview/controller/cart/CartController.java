package ex.soft.webview.controller.cart;

import ex.soft.domain.model.Cart;
import ex.soft.domain.model.Phone;
import ex.soft.service.PhoneService;
import ex.soft.service.api.ICartService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by Alex108 on 19.10.2016.
 */
@RequestMapping("/cart")
@Controller
public class CartController {

    private static final Logger LOGGER = Logger.getLogger(CartController.class);

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private ICartService cartService;

    @ModelAttribute("cart")
    public Cart showCartWidget(HttpSession session) {
        LOGGER.info("--------------------------------");
        LOGGER.info("SHOW_CART_WIDGET");
        Cart cart = cartService.getCart(session);
        LOGGER.info(cart.getOrderItems());
        LOGGER.info("--------------------------------");
        return cart;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showCartPage(){
        return "cart/cart";
    }

    @RequestMapping(value = "/addProductToCart/{key:[\\d]+}", method = RequestMethod.POST)
    public @ResponseBody String addProductToCart(@RequestParam String quantity,
                                                 @PathVariable("key") Long productId,
                                                 HttpSession session ){
        LOGGER.info("Add product to cart");
        LOGGER.info("key: " + productId);
        LOGGER.info("quantity: " + quantity);

        Long qty = null;
        try{
            qty = Long.valueOf(quantity);
        } catch (NumberFormatException e){
            LOGGER.info("Error: Quantity must be a number");
            return "Error: Quantity must be a number";
        }
        if (qty <= 0) return "Error: Quantity must be a positive number";
        Phone phone = phoneService.getProduct(productId);
        cartService.addToCart(session, phone, qty);

        LOGGER.info(phone.getModel() + " in " + quantity + " items, added to cart");
        return phone.getModel() + " in " + quantity + " items, added to cart";
    }

    @RequestMapping(value = "delete/{key}", method = RequestMethod.POST)
    public String deleteProductFromCart(@PathVariable Long key, HttpSession session){
        LOGGER.info("Delete product from cart with key=" + key);
        Long result = cartService.deleteFromCart(session, key);
        LOGGER.info("Product with key=" + key + " in " + result + " items, deleted from cart");
        return "cart/cart";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateProductsInCart(Cart cart, BindingResult result, HttpSession session ){
        LOGGER.info("Update products in cart");
        LOGGER.info(cart.getOrderItems());
        LOGGER.info(result.getTarget());
        LOGGER.info(result.getObjectName());
        LOGGER.info(result.getModel());
        LOGGER.info(result.toString());
        if(result.hasErrors()){
            LOGGER.info("ERROR:" + result.toString());
            return "cart/cart";
        } else {
            cartService.updateCart(session, cart);
            LOGGER.info( cart.getTotalQuantity() + " items, updated in cart");
            return "cart/cart";
        }
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String forwardToOrderPage(){
        return "order/order";
    }

    @RequestMapping(value = "/getCartJson", method = RequestMethod.GET)
    public @ResponseBody String getCartJson(HttpSession session){
        LOGGER.info("GET_JSON");
        Cart cart =  cartService.getCart(session);
        String data = String.format("{\"quantity\":%d, \"price\":%s}", cart.getTotalQuantity(), cart.getTotalPrice());
        LOGGER.info("DATA: " + data);
        LOGGER.info("SUCCESS");
        return data;
    }

}
