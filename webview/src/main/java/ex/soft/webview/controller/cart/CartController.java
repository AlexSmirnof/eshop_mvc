package ex.soft.webview.controller.cart;

import ex.soft.domain.form.CartForm;
import ex.soft.domain.model.Cart;
import ex.soft.domain.model.Phone;
import ex.soft.service.api.CartService;
import ex.soft.service.api.ProductService;
import ex.soft.service.form.CartFormService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    public  static final String CART_FORM_ATTRIBUTE = "cartForm";
    private static final String SUCCESS_FLASH_ATTRIBUTE = "successMessage";
    private static final String UPDATE_CART_SUCCESS_FLASH_MESSAGE = "Cart was successfully updated.";
    private static final String DELETE_FROM_CART_SUCCESS_MESSAGE = "Product was successfully deleted.";
    private static final String ADD_TO_CART_ERROR_MESSAGE = "Error: Quantity must be a positive number.";

    private static final Logger LOGGER = Logger.getLogger(CartController.class);

    @Autowired
    private ProductService<Phone> phoneService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartFormService cartFormService;
    @Autowired
    @Qualifier("cartFormValidator")
    private Validator cartFormValidator;


    @InitBinder
    private void initBinder(WebDataBinder binder){
        binder.setValidator(cartFormValidator);
    }

    @ModelAttribute(CART_FORM_ATTRIBUTE)
    public CartForm showCartWidget(HttpSession session) {
        Cart cart = cartService.getCart(session);
        return cartFormService.mapCartToCartForm(cart);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showCartPage(){
        return "cart/cart";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateProductsInCart(@Valid @ModelAttribute CartForm cartForm, BindingResult result,
                                       HttpSession session, RedirectAttributes redirectAttributes){
        if(LOGGER.isEnabledFor(Level.INFO)){
            LOGGER.info("Update products in cart");
            LOGGER.info(cartForm);
        }
        if(result.hasErrors()){
            if(LOGGER.isEnabledFor(Level.INFO)){
                LOGGER.info("ERROR:" + result.toString());
            }
            return "cart/cart";
        } else {
            cartService.updateCart(session, cartForm);
            redirectAttributes.addFlashAttribute(SUCCESS_FLASH_ATTRIBUTE, UPDATE_CART_SUCCESS_FLASH_MESSAGE);
            if(LOGGER.isEnabledFor(Level.INFO)){
                LOGGER.info( cartForm.getOrderItemForms().size() + " items, updated in form");
            }
            return "redirect:/cart";
        }
    }

    @RequestMapping(value = "delete/{key}", method = RequestMethod.POST)
    public String deleteProductFromCart(@PathVariable Long key, HttpSession session,
                                        RedirectAttributes redirectAttributes){
        if(LOGGER.isEnabledFor(Level.INFO)){
            LOGGER.info("Delete product from cart with key=" + key);
        }
        Long result = cartService.deleteFromCart(session, key);
        redirectAttributes.addFlashAttribute(SUCCESS_FLASH_ATTRIBUTE, DELETE_FROM_CART_SUCCESS_MESSAGE);
        if(LOGGER.isEnabledFor(Level.INFO)){
            LOGGER.info("Product with key=" + key + " in " + result + " items, deleted from cart");
        }
        return "redirect:/cart";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String forwardToOrderPage(){
        return "redirect:/order";
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

    @RequestMapping(value = "/getCartJson", method = RequestMethod.GET)
    public @ResponseBody String getCartJson(HttpSession session){
        if(LOGGER.isEnabledFor(Level.INFO)){
            LOGGER.info("Get json data");
        }
        Cart cart =  cartService.getCart(session);
        String data = String.format("{\"quantity\":%d, \"price\":%s}", cart.getTotalQuantity(), cart.getTotalPrice());
        if(LOGGER.isEnabledFor(Level.INFO)){
            LOGGER.info("Data: " + data);
        }
        return data;
    }

}
