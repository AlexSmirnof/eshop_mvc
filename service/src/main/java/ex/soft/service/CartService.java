package ex.soft.service;

import ex.soft.domain.model.Cart;
import ex.soft.domain.model.OrderItem;
import ex.soft.domain.model.Phone;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Alex108 on 19.10.2016.
 */
@Service
public class CartService {

    @Transactional
    public Cart getCart(HttpSession session){
       return getCartSafely(session);
    }

    @Transactional
    public void addProductToCart(HttpSession session, Phone phone, Long quantity){
        Cart cart = getCartSafely(session);
        updateCartWithNewProduct(cart, phone, quantity);
        session.setAttribute("cart", cart);
    }

    @Transactional
    public void deleteProductFromCart(HttpSession session, Phone phone, Long quantity){
        Cart cart = getCartSafely(session);
        updateCartWithoutProduct(cart, phone, quantity);
        session.setAttribute("cart", cart);
    }


    private static Cart getCartSafely(HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        System.out.println(cart);
        return cart != null ? cart : new Cart();
    }

    private static void updateCartWithNewProduct(Cart cart, Phone phone, Long quantity){
        int index = findIndexOfProductInCart(cart, phone);
        if( index < 0 ){
            cart.getOrderItems().add(new OrderItem(phone, quantity));
        } else {
            Long oldQuantity = cart.getOrderItems().get(index).getQuantity();
            Long newQuantity = Long.sum(oldQuantity, quantity);
            cart.getOrderItems().get(index).setQuantity(newQuantity);
        }
        updateCartTotalQuantity(cart, quantity);
        System.out.println(cart.getTotalPrice());
        updateCartTotalPrice(cart, phone.getPrice(), quantity, false);
        System.out.println(cart.getTotalPrice());
    }

    private static void updateCartWithoutProduct(Cart cart, Phone phone, Long quantity){
        int index = findIndexOfProductInCart(cart, phone);
        Long oldQuantity = cart.getOrderItems().get(index).getQuantity();
        if (oldQuantity > quantity) {
            cart.getOrderItems().get(index).setQuantity(Long.sum(oldQuantity, -quantity));
            updateCartTotalQuantity(cart, -quantity);
            updateCartTotalPrice(cart, phone.getPrice(), quantity, true);
        } else {
            cart.getOrderItems().remove(index);
            updateCartTotalQuantity(cart, -oldQuantity);
            updateCartTotalPrice(cart, phone.getPrice(), oldQuantity, true);
        }
    }

    private static int findIndexOfProductInCart(Cart cart, Phone phone){
        List<OrderItem> orderItems = cart.getOrderItems();
        int index = 0;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getPhone().equals(phone)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private static void updateCartTotalQuantity(Cart cart, Long quantity){
        Long totalQuantity = Long.sum(cart.getTotalQuantity(), quantity);
        cart.setTotalQuantity(totalQuantity);
    }

    private static void updateCartTotalPrice(Cart cart, BigDecimal productPrice, Long quantity, boolean subtract){
        BigDecimal oldTotalPrice = cart.getTotalPrice();
        productPrice = productPrice.multiply(BigDecimal.valueOf(quantity.longValue()));
        BigDecimal totalPrice = subtract ? oldTotalPrice.subtract(productPrice) : oldTotalPrice.add(productPrice);
        cart.setTotalPrice(totalPrice);
    }



}
