package ex.soft.webview.controller.cart;

import ex.soft.domain.model.Cart;
import ex.soft.domain.model.OrderItem;
import ex.soft.domain.model.Phone;
import ex.soft.service.PhoneService;
import ex.soft.service.api.ICartService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        LOGGER.info("--------------------------------");
        return cartService.getCart(session);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showCartPage(){
        return "cart/cart";
    }

    @RequestMapping(value = "/addProductToCart/{key:[\\d]+}", method = RequestMethod.POST)
    public @ResponseBody String addProductToCart(@Valid @Size(min = 1) @ModelAttribute("quantity") Long quantity, BindingResult result,
                                                 @PathVariable("key") Long productId,
                                                 HttpSession session ){
        LOGGER.info("ADD_TO_CART");
        LOGGER.info("key: " + productId);
        LOGGER.info("quantity: " + quantity);
        if (result.hasErrors()){
            LOGGER.error(result.toString());
            return "Error: Invalid quantity number format";
        } else if (quantity <= 0){
            LOGGER.error("Quantity must be greater than 0");
            return "Error: Quantity must be greater than 0";
        } else {
            Phone phone = phoneService.getProduct(productId);
            OrderItem orderItem = new OrderItem();
            orderItem.setPhone(phone);
            orderItem.setQuantity(quantity);
            cartService.addToCart(session, orderItem);
            LOGGER.info(phone.getModel() + " in " + quantity + " items, added to cart");
            return phone.getModel() + " in " + quantity + " items, added to cart";
        }
    }

    @RequestMapping(value = "delete/{key}", method = RequestMethod.POST)
    public String deleteProductFromCart(@RequestParam Map<String, String> params,
                                        @PathVariable Long key,
                                        HttpSession session){
        LOGGER.info("DELETE_FROM_CART");
        LOGGER.info("params: "  + params);
        LOGGER.info("key: " + key);
        String value = params.get(key.toString());
        LOGGER.info("value: " + value);
        if (value == null) throw new RuntimeException("Can`t associate product with quantity");
        Long quantity = null;
        try{
            quantity = Long.valueOf(value);
            LOGGER.info("quantity: " + quantity);
        } catch (NumberFormatException e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException("Incorrect quantity number format");
        }
        if (quantity <= 0) throw new RuntimeException("Quantity must be greater than 0");
        Phone phone = phoneService.getProduct(key);
        OrderItem orderItem = new OrderItem();
        orderItem.setPhone(phone);
        orderItem.setQuantity(quantity);
        Long res = cartService.deleteFromCart(session, orderItem);
        LOGGER.info(phone.getModel() + " in "+ res + " items, deleted from cart");
        return "cart/cart";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateProductsInCart(@RequestParam Map<String, String> params,
                                       HttpSession session ){
        LOGGER.info("UPDATE_CART");
        LOGGER.info("params: "  + params);
        List<OrderItem> orderItems = new ArrayList<>();
        try{
            for (Map.Entry<String, String> entry : params.entrySet()) {
                Long key = Long.valueOf(entry.getKey());
                Long quantity = Long.valueOf(entry.getValue());
                LOGGER.info("key=" + key + ", quantity=" + quantity);
                if (quantity <= 0) throw new RuntimeException("Quantity must be greater than 0");
                Phone phone = phoneService.getProduct(key);
                OrderItem orderItem = new OrderItem();
                orderItem.setPhone(phone);
                orderItem.setQuantity(quantity);
                orderItems.add(orderItem);
            }
        } catch (NumberFormatException e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException("Incorrect quantity number format");
        }
        cartService.updateCart(session, orderItems);
        LOGGER.info( orderItems.size() + " items, updated in cart");
        return "cart/cart";
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
