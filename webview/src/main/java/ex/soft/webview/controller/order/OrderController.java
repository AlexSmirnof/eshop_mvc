package ex.soft.webview.controller.order;

import ex.soft.domain.model.Order;
import ex.soft.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Alex108 on 08.11.2016.
 */
@RequestMapping("/order")
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    private static final Logger LOGGER = Logger.getLogger(OrderController.class);

    @ModelAttribute("order")
    public Order orderModel(HttpSession session) {
        LOGGER.info("--------------------------------");
        LOGGER.info("SET_ORDER_MODEL");
        LOGGER.info("--------------------------------");
        return orderService.getOrder(session);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showOrderPage(){
        LOGGER.info("Show Order Page");
        return "order/order";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String placeOrder(Model model){
        LOGGER.info("Place Order");
        LOGGER.info("model: " + model.asMap());
//        orderService.placeOrder();
        return "order/order";
    }

}
