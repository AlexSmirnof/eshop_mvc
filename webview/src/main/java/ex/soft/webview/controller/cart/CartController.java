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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by Alex108 on 19.10.2016.
 */
@RequestMapping("/cart")
@Controller
public class CartController {

    private static final String UPDATE_CART_SUCCESS_MESSAGE = "Cart was successfully updated";
    private static final String DELETE_FROM_CART_SUCCESS_MESSAGE = "Product was successfully deleted";
    private static final String ADD_TO_CART_ERROR_MESSAGE = "Error: Quantity must be a positive number";

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

//        binder.setConversionService(conversionService);

//        binder.registerCustomEditor(Cart.class, "orderItems", new PropertyEditorSupport(){
//            @Override
//            public void setAsText(String text) throws IllegalArgumentException {
//                super.setAsText(text);
//            }
//        });

        binder.setValidator(cartValidator);
    }

    @ModelAttribute("cart")
    public Cart showCartWidget(HttpSession session) {
        return cartService.getCart(session);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showCartPage(){
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
            if (qty <= 0) return ADD_TO_CART_ERROR_MESSAGE;
        } catch (NumberFormatException e){
            if(LOGGER.isEnabledFor(Level.INFO)){
                LOGGER.info("Error: Quantity must be a number");
            }
            return ADD_TO_CART_ERROR_MESSAGE;
        }
        Phone phone = phoneService.getProduct(productId);
        cartService.addToCart(session, phone, qty);
        if(LOGGER.isEnabledFor(Level.INFO)){
            LOGGER.info(phone.getModel() + " in " + quantity + " items, added to cart");
        }
        return phone.getModel() + " in " + quantity + " items, added to cart";
    }

    @RequestMapping(value = "delete/{key}", method = RequestMethod.POST)
    public String deleteProductFromCart(@PathVariable Long key,
                                        HttpSession session, RedirectAttributes redirectAttributes){
        if(LOGGER.isEnabledFor(Level.INFO)){
            LOGGER.info("Delete product from cart with key=" + key);
        }
        Long result = cartService.deleteFromCart(session, key);
        redirectAttributes.addFlashAttribute("flashMessage", DELETE_FROM_CART_SUCCESS_MESSAGE);
        if(LOGGER.isEnabledFor(Level.INFO)){
            LOGGER.info("Product with key=" + key + " in " + result + " items, deleted from cart");
        }
        return "redirect:/cart";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateProductsInCart(@ModelAttribute("cart") @Valid Cart cart, BindingResult result,
                                       HttpSession session, RedirectAttributes redirectAttributes){
        if(LOGGER.isEnabledFor(Level.INFO)){
            LOGGER.info("Update products in cart");
        }
        if(result.hasErrors()){
            if(LOGGER.isEnabledFor(Level.INFO)){
                LOGGER.info("ERROR:" + result.toString());
                LOGGER.info(result.getFieldError());
                LOGGER.info(result.getFieldError().getObjectName());
                LOGGER.info(result.getFieldError().getField());
                LOGGER.info(result.getFieldError().getRejectedValue());
                LOGGER.info(result.getFieldError().getDefaultMessage());
                LOGGER.info(result.getFieldError().getCode());
                LOGGER.info(result.getFieldError().getArguments()[0]);
                LOGGER.info(result.getFieldErrors());
            }
            return "cart/cart";
        } else {
            cartService.updateCart(session, cart);
            redirectAttributes.addFlashAttribute("flashMessage", UPDATE_CART_SUCCESS_MESSAGE);
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
