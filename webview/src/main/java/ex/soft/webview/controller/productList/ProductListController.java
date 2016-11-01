package ex.soft.webview.controller.productList;


import ex.soft.domain.model.Cart;
import ex.soft.domain.model.Phone;
import ex.soft.service.CartService;
import ex.soft.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Alex108 on 12.10.2016.
 */
@RequestMapping("/productList")
@Controller
@SessionAttributes("cart")
public class ProductListController {

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private CartService cartService;

    @ModelAttribute("cart")
    public Cart showCartWidget(HttpSession session) {
        System.out.println("show cart widget");
        return cartService.getCart(session);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showProductList(Model model){
        List<Phone> productList = phoneService.listProducts();
        model.addAttribute("productList", productList);
        System.out.println("PRODUCTS: " + productList);
        return "productList/productList";
    }

}
