package ex.soft.webview.controller.cartPage;

import ex.soft.domain.model.Cart;
import ex.soft.domain.model.Client;
import ex.soft.domain.model.Phone;
import ex.soft.domain.model.Product;
import ex.soft.service.CartService;
import ex.soft.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Alex108 on 19.10.2016.
 */
@RequestMapping("/cartPage")
@Controller
public class CartPageController {

    @Autowired
    @Qualifier("productService")
    private ProductService productService;
    @Autowired
    @Qualifier("cartService")
    private CartService cartService;

    @ModelAttribute("cart")
    public Cart showCartButton(HttpSession session) {
        return cartService.getCart(Long.valueOf(session.getId()));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showCartPage(HttpSession session){
        ModelAndView mav = new ModelAndView("cartPage");
        Long clientId = Long.valueOf(session.getId());
        Map<Phone, Integer> cartItems = cartService.getCart(clientId).getProductsAndQuantities();
        mav.addObject("cartItems", cartItems);
        return mav;
    }



}
