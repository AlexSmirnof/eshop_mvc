package ex.soft.service.api;

import ex.soft.domain.form.CartForm;
import ex.soft.domain.model.Cart;
import ex.soft.domain.model.Phone;

import javax.servlet.http.HttpSession;

/**
 * Created by Alex108 on 14.11.2016.
 */
public interface CartService {

    Cart getCart(HttpSession session);

   void removeCart(HttpSession session);

    void updateCart(HttpSession session, CartForm cartForm);

    Long addToCart(HttpSession session, Phone phone, Long quantity);

    Long deleteFromCart(HttpSession session, Long key);
}
