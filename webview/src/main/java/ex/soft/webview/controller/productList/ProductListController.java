package ex.soft.webview.controller.productList;


import ex.soft.domain.model.Cart;
import ex.soft.domain.model.Client;
import ex.soft.domain.model.Phone;
import ex.soft.service.CartService;
import ex.soft.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Alex108 on 12.10.2016.
 */
@RequestMapping("/productList")
@Controller
public class ProductListController {

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
    public ModelAndView showProductList(){
        ModelAndView modelAndView = new ModelAndView("productList");
        List<Phone> productList = productService.listProducts();
        modelAndView.addObject("productList", productList);
        return modelAndView;
    }

    @RequestMapping(value = "/add/{key}", method = RequestMethod.POST)
    public String addProductToCart(@ModelAttribute("quantity") @Valid @Size(min = 1) Integer quantity, BindingResult result,
                                   @PathVariable("key") Long productId,
                                   HttpSession session,
                                   Model model){
        if (result.hasErrors()){
            model.addAttribute("errors", "Invalid quantity number");
            return "productList";
        } else {
            Long clientId = Long.valueOf(session.getId());
            cartService.addToCart(clientId, productId, quantity);
            return "productList";
        }
    }
}
