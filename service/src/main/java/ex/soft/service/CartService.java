package ex.soft.service;

import ex.soft.domain.model.Cart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Alex108 on 19.10.2016.
 */
@Service("cartService")
public class CartService {


    @Transactional
    public Cart getCart(){
        return null;
    }

    @Transactional
    public void addToCart(){}

}
