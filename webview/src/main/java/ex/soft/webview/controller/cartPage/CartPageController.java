package ex.soft.webview.controller.cartPage;

import ex.soft.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Alex108 on 19.10.2016.
 */
@RequestMapping("/cartPage")
@Controller
public class CartPageController {

    @Autowired
    private CartService cartService;

    public String showCartPage(){
        return "cartPage";
    }

    public String addToCart(){
        return "cartPage";
    }

}
