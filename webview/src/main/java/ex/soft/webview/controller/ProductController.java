package ex.soft.webview.controller;


import ex.soft.domain.model.Phone;
import ex.soft.service.manager.OrderService;
import ex.soft.service.manager.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Alex108 on 12.10.2016.
 */
@Controller
public class ProductController {

    private PhoneService phoneService;
    private OrderService orderService;

    @Autowired
    @Qualifier("phoneService")
    public void setPhoneService(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @Autowired
    @Qualifier("orderService")
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     *     ??? под вопросом
     */
    @RequestMapping(value = "/Cart", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("phone") Phone phone){
        phoneService.save(phone);
        return "redirect:/OrderConfirmation";
    }

    @RequestMapping("/ProductList")
    public String productList(Model model){
        model.addAttribute("phonesList", phoneService.findAll());
        return "ProductList";
    }

    @RequestMapping("/ProductDetails/{key}")
    public String productDetails(@PathVariable("key") long key, Model model){
        model.addAttribute("phone", phoneService.get(key));
        return "ProductDetails";
    }
}
