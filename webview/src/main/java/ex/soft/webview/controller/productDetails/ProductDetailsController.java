package ex.soft.webview.controller.productDetails;

import com.sun.org.glassfish.gmbal.ParameterNames;
import ex.soft.domain.model.Phone;
import ex.soft.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Alex108 on 19.10.2016.
 */
@RequestMapping("/productDetails")
@Controller
public class ProductDetailsController {

    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    @RequestMapping(value = "/{model}/{key}", method = RequestMethod.GET)
    public String showProductDetails(@PathVariable("model") String model,
                                     @PathVariable("key")   Long key,
                                                            ModelMap modelMap ){
        Phone product = productService.getProduct(key);
        modelMap.addAttribute("product", product);
        return "productDetails";
    }

    @RequestMapping(value = "/add/{key}", method = RequestMethod.POST)
    public String addProductToCart(@ModelAttribute @Valid Integer quantity, BindingResult result,
                                   @PathVariable("key") Long productId                           ){

        return "";
    }
}
