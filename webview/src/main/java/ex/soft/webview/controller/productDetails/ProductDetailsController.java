package ex.soft.webview.controller.productDetails;

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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by Alex108 on 19.10.2016.
 */
@RequestMapping("/productDetails")
@Controller
public class ProductDetailsController {

    private static final String PRODUCT_ATTRIBUTE = "product";

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
    public String showProductDetails( @RequestParam("model") String model,
                                      @RequestParam("key")   Long productId,
                                                             Model modelView ){
        Phone product = phoneService.getProduct(productId);
        modelView.addAttribute(PRODUCT_ATTRIBUTE, product);
        return "productDetails/productDetails";
    }

}
