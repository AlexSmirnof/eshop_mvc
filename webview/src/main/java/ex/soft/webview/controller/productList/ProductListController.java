package ex.soft.webview.controller.productList;


import ex.soft.domain.form.CartForm;
import ex.soft.domain.model.Cart;
import ex.soft.domain.model.Phone;
import ex.soft.service.api.CartService;
import ex.soft.service.api.ProductService;
import ex.soft.service.form.CartFormService;
import ex.soft.webview.controller.cart.CartController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Alex108 on 12.10.2016.
 */
@RequestMapping("/productList")
@Controller
public class ProductListController {

    private static final String PRODUCT_LIST_ATTRIBUTE = "productList";

    @Autowired
    private ProductService<Phone> phoneService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartFormService cartFormService;


    @ModelAttribute(CartController.CART_FORM_ATTRIBUTE)
    public CartForm showCartWidget(HttpSession session) {
        Cart cart = cartService.getCart(session);
        return cartFormService.mapCartToCartForm(cart);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showProductList(Model model){
        List<Phone> productList = phoneService.listProducts();
        model.addAttribute(PRODUCT_LIST_ATTRIBUTE, productList);
        return "productList/productList";
    }

}
