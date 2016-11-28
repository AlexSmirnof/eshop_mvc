package ex.soft.webview.controller.order;

import ex.soft.domain.form.OrderForm;
import ex.soft.domain.model.Cart;
import ex.soft.service.api.CartService;
import ex.soft.service.api.OrderService;
import ex.soft.service.form.OrderFormService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by Alex108 on 08.11.2016.
 */
@RequestMapping("/order")
@Controller
public class OrderController {

    private static final String ORDER_FORM_ATTRIBUTE = "orderForm";
    private static final Logger LOGGER = Logger.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderFormService orderFormService;

    @ModelAttribute(OrderController.ORDER_FORM_ATTRIBUTE)
    public OrderForm setOrderFormToModel(HttpSession session){
        Cart cart = cartService.getCart(session);
        return orderFormService.mapCartToOrderForm(cart);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showOrderPage(Model model, HttpSession session){
        return "order/order";
    }

    @RequestMapping(value = "confirm", method = RequestMethod.POST)
    public String placeOrder(@ModelAttribute @Valid OrderForm orderForm, BindingResult result,
                             HttpSession session, RedirectAttributes redirectAttributes) {
        if(LOGGER.isEnabledFor(Level.INFO)){
            LOGGER.info("Place Order");
            LOGGER.info(orderForm);
        }
        if(result.hasErrors()){
            if(LOGGER.isEnabledFor(Level.INFO)){
                LOGGER.info("Error: " + result.getFieldErrors());
            }
            return "order/order";
        }
        Long key = orderService.placeOrder(session, orderForm);
        return "redirect:/orderConfirmation/" + key;
    }

 }
