package ex.soft.service.api;

import ex.soft.domain.model.Cart;
import ex.soft.domain.model.Phone;

import javax.servlet.http.HttpSession;

/**
 * Created by Alex108 on 14.11.2016.
 */
public interface ICartService {

    Cart getCart(HttpSession session);

   void removeCart(HttpSession session);

    void updateCart(HttpSession session, Cart cart);

    Long addToCart(HttpSession session, Phone phone, Long quantity);

    Long deleteFromCart(HttpSession session, Long key);
}
