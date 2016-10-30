package ex.soft.webview.controller.productDetails;

import ex.soft.domain.model.Cart;
import ex.soft.domain.model.Phone;
import ex.soft.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by Alex108 on 19.10.2016.
 */
@RequestMapping("/productDetails")
@Controller
@SessionAttributes("cart")
public class ProductDetailsController {

    @Autowired
    @Qualifier("phoneService")
    private PhoneService phoneService;

    @ModelAttribute("cart")
    public Cart showCartWidget(HttpSession session) {
        System.out.println("ModelAttribute");
        Cart cart = (Cart) session.getAttribute("cart");
        return cart != null ? cart : new Cart();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showProductDetails( @RequestParam("model") String model,
                                            @RequestParam("key")   Long productId){
        ModelAndView mav = new ModelAndView("productDetails/productDetails");
        Phone product = phoneService.getProduct(productId);
        mav.addObject("product", product);
        return mav;
    }

}
