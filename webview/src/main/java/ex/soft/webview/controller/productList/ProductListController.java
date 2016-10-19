package ex.soft.webview.controller.productList;


import ex.soft.domain.model.Phone;
import ex.soft.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    public ModelAndView showProductList(){
        ModelAndView modelAndView = new ModelAndView("productList");
        List<Phone> productList = productService.listProducts();
        modelAndView.addObject("productList", productList);
        return modelAndView;
    }

}
