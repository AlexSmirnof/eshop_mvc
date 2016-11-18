package ex.soft.webview.controller.order;

import ex.soft.domain.model.Order;
import ex.soft.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by Alex108 on 08.11.2016.
 */
@RequestMapping("/order")
@Controller
public class OrderController {

    private static final Logger LOGGER = Logger.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public String showOrderPage(Model model, HttpSession session){
        LOGGER.info("Show Order Page");
        Order order = orderService.createOrder(session);
        model.addAttribute("order", order);
        LOGGER.info(order);
        return "order/order";
    }

    @RequestMapping(value = "confirm", method = RequestMethod.POST)
    public String placeOrder(@Valid Order order, BindingResult result, HttpSession session){
        LOGGER.info("Place Order");
        LOGGER.info(order);
        Long key = orderService.placeOrder(session, order);
        return "redirect:/orderConfirmation/" + key;
    }

 }
