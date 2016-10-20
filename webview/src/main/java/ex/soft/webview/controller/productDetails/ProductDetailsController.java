package ex.soft.webview.controller.productDetails;

import ex.soft.domain.model.Cart;
import ex.soft.domain.model.Phone;
import ex.soft.service.CartService;
import ex.soft.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * Created by Alex108 on 19.10.2016.
 */
@RequestMapping("/productDetails")
@Controller
public class ProductDetailsController {

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


    @RequestMapping(value = "/{model}/{key}", method = RequestMethod.GET)
    public String showProductDetails(@PathVariable("model") String model,
                                     @PathVariable("key")   Long productId,
                                                            ModelMap modelMap ){
        Phone product = productService.getProduct(productId);
        modelMap.addAttribute("product", product);
        return "productDetails/" + model + "/" + productId;
    }

    @RequestMapping(value = "/{model/{key}/add", method = RequestMethod.POST)
    public String addProductToCart(@ModelAttribute @Valid @Size(min=1) Integer quantity, BindingResult result,
                                   @PathVariable("key") Long productId,
                                   HttpSession session,
                                   Model model){
        if (result.hasErrors()){
            model.addAttribute("errors", "Invalid quantity number");
            return "productDetails/" + model + "/" + productId;
        } else {
            Long clientId = Long.valueOf(session.getId());
            cartService.addToCart(clientId, productId, quantity);
            return "productDetails/" + model + "/" + productId;
        }
    }
}
