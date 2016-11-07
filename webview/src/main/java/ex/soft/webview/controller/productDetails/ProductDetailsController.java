package ex.soft.webview.controller.productDetails;

import ex.soft.domain.model.Cart;
import ex.soft.domain.model.Phone;
import ex.soft.service.PhoneService;
import ex.soft.service.api.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by Alex108 on 19.10.2016.
 */
@RequestMapping("/productDetails")
@Controller
public class ProductDetailsController {

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private ICartService cartService;

    @ModelAttribute("cart")
    public Cart showCartWidget(HttpSession session) {
        return cartService.getCart(session);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showProductDetails( @RequestParam("model") String model,
                                      @RequestParam("key")   Long productId,
                                      Model modelView){
        Phone product = phoneService.getProduct(productId);
        modelView.addAttribute("product", product);
        return "productDetails/productDetails";
    }

}
