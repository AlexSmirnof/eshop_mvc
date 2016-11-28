package ex.soft.webview.controller.orderConfirmation;

import ex.soft.domain.model.Order;
import ex.soft.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Alex108 on 14.11.2016.
 */
@RequestMapping("/orderConfirmation")
@Controller
public class OrderConfirmationController {

    private static final String ORDER_ATTRIBUTE = "order";

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "{key:[\\d]+}", method = RequestMethod.GET)
    public String showOrderConfirmation(@PathVariable Long key, Model model){
        Order order = orderService.getOrder(key);
        model.addAttribute(ORDER_ATTRIBUTE, order);
        return "orderConfirmation/orderConfirmation";
    }

}
