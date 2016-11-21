package ex.soft.webview.controller.cart;

import ex.soft.domain.model.Cart;
import ex.soft.domain.model.Phone;
import ex.soft.service.api.CartService;
import ex.soft.service.api.ProductService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by Alex108 on 19.10.2016.
 */
@RequestMapping("/cart")
@Controller
public class CartController {

    private static final Logger LOGGER = Logger.getLogger(CartController.class);

    @Autowired
    private ProductService<Phone> phoneService;
    @Autowired
    private CartService cartService;
    @Autowired
    @Qualifier("cartValidator")
    private Validator cartValidator;
    @Autowired
    private ConversionService conversionService;

    @InitBinder
    private void initBinder(WebDataBinder binder){
        if(LOGGER.isEnabledFor(Level.INFO)){
            LOGGER.info("IniBinder");
            LOGGER.info(conversionService);
        }
        binder.setConversionService(conversionService);
        binder.setValidator(cartValidator);
    }

    @ModelAttribute("cart")
    public Cart showCartWidget(HttpSession session) {
        if(LOGGER.isEnabledFor(Level.INFO)){
            LOGGER.info("Show cart widget");
        }
        return cartService.getCart(session);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showCartPage(){
        if(LOGGER.isEnabledFor(Level.INFO)){
            LOGGER.info("Show cart page");
        }
        return "cart/cart";
    }

    @RequestMapping(value = "/addProductToCart/{key:[\\d]+}", method = RequestMethod.POST)
    public @ResponseBody String addProductToCart(@RequestParam String quantity,
                                                 @PathVariable("key") Long productId,
                                                 HttpSession session ){
        if(LOGGER.isEnabledFor(Level.INFO)){
            LOGGER.info("Add product to cart");
        }
        Long qty = null;
        try{
            qty = Long.valueOf(quantity);
        } catch (NumberFormatException e){
            if(LOGGER.isEnabledFor(Level.INFO)){
                LOGGER.info("Error: Quantity must be a number");
            }
            return "Error: Quantity must be a number";
        }
        if (qty <= 0) return "Error: Quantity must be a positive number";
        Phone phone = phoneService.getProduct(productId);
        cartService.addToCart(session, phone, qty);
        if(LOGGER.isEnabledFor(Level.INFO)){
            LOGGER.info(phone.getModel() + " in " + quantity + " items, added to cart");
        }
        return phone.getModel() + " in " + quantity + " items, added to cart";
    }

    @RequestMapping(value = "delete/{key}", method = RequestMethod.POST)
    public String deleteProductFromCart(@PathVariable Long key, HttpSession session){
        if(LOGGER.isEnabledFor(Level.INFO)){
            LOGGER.info("Delete product from cart with key=" + key);
        }
        Long result = cartService.deleteFromCart(session, key);
        if(LOGGER.isEnabledFor(Level.INFO)){
            LOGGER.info("Product with key=" + key + " in " + result + " items, deleted from cart");
        }
        return "redirect:/cart";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateProductsInCart(@ModelAttribute("cart") @Valid Cart cart, BindingResult result, HttpSession session ){
        if(LOGGER.isEnabledFor(Level.INFO)){
            LOGGER.info("Update products in cart");
        }
        if(result.hasErrors()){
            if(LOGGER.isEnabledFor(Level.INFO)){
                LOGGER.info("ERROR:" + result.toString());
            }
            return "cart/cart";
        } else {
            cartService.updateCart(session, cart);
            if(LOGGER.isEnabledFor(Level.INFO)){
                LOGGER.info( cart.getTotalQuantity() + " items, updated in cart");
            }
            return "redirect:/cart";
        }
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String forwardToOrderPage(){
        return "redirect:/order";
    }

    @RequestMapping(value = "/getCartJson", method = RequestMethod.GET)
    public @ResponseBody String getCartJson(HttpSession session){
        if(LOGGER.isEnabledFor(Level.INFO)){
            LOGGER.info("Get json");
        }
        Cart cart =  cartService.getCart(session);
        String data = String.format("{\"quantity\":%d, \"price\":%s}", cart.getTotalQuantity(), cart.getTotalPrice());
        if(LOGGER.isEnabledFor(Level.INFO)){
            LOGGER.info("Data: " + data);
        }
        return data;
    }

}
