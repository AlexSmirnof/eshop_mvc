package ex.soft.service;

import ex.soft.domain.model.Cart;
import ex.soft.domain.model.OrderItem;
import ex.soft.domain.model.Phone;
import ex.soft.service.api.ICartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Alex108 on 19.10.2016.
 */
@Service
public class CartService implements ICartService {

    private static final String CART_ATTRIBUTE_NAME = "cart";

    @Resource
    private Cart cart;

    @Override
    public Cart getCart(HttpSession session){
        return getCartSafely(session);
    }

    @Override
    public void removeCart(HttpSession session){
       session.removeAttribute(CART_ATTRIBUTE_NAME);
    }

    @Override
    public void updateCart(HttpSession session, List<OrderItem> orderItems) {
        Cart cart = getCartSafely(session);
        cart.setOrderItems(orderItems);
        updateCartTotalPriceAndQuantity(cart);
        session.setAttribute(CART_ATTRIBUTE_NAME, cart);
    }

    @Override
    public Long addToCart(HttpSession session, OrderItem orderItem){
        Cart cart = getCartSafely(session);
        updateCartWithNewProduct(cart, orderItem.getPhone(), orderItem.getQuantity());
        session.setAttribute(CART_ATTRIBUTE_NAME, cart);
        return orderItem.getQuantity();
    }

    @Override
    public Long deleteFromCart(HttpSession session, OrderItem orderItem){
        Cart cart = getCartSafely(session);
        Long res = updateCartWithoutProduct(cart, orderItem.getPhone(), orderItem.getQuantity());
        session.setAttribute(CART_ATTRIBUTE_NAME, cart);
        return res;
    }


    private Cart getCartSafely(HttpSession session){
        Cart cart = (Cart) session.getAttribute(CART_ATTRIBUTE_NAME);
        return cart != null ? cart : new Cart();
    }

    private void updateCartWithNewProduct(Cart cart, Phone phone, Long quantity){
        int index = findIndexOfProductInCart(cart.getOrderItems(), phone);
//  if phone not exist in cart
        if( index < 0 ){
            OrderItem newOrderItem = new OrderItem();
            newOrderItem.setPhone(phone);
            newOrderItem.setQuantity(quantity);
            cart.getOrderItems().add(newOrderItem);
//  phone already exist in cart so we increase oldQuantity of that phone
        } else {
            Long oldQuantity = cart.getOrderItems().get(index).getQuantity();
            Long newQuantity = Long.sum(oldQuantity, quantity);
            cart.getOrderItems().get(index).setQuantity(newQuantity);
        }
        updateCartTotalPriceAndQuantity(cart);
    }

    private Long updateCartWithoutProduct(Cart cart, Phone phone, Long quantity){
        int index = findIndexOfProductInCart(cart.getOrderItems(), phone);
//  if phone not exist in cart -> index = -1
        if ( index < 0 ) {
            throw new RuntimeException("This product is already deleted.");
        }
        Long oldQuantity = cart.getOrderItems().get(index).getQuantity();
//  if input quantity greater or equal to real quantity of phone in cart
        if (oldQuantity <= quantity) {
            quantity = oldQuantity;
            cart.getOrderItems().remove(index);
        } else {
            Long newQuantity = Long.sum(oldQuantity, -quantity);
            cart.getOrderItems().get(index).setQuantity(newQuantity);
        }
        updateCartTotalPriceAndQuantity(cart);
        return quantity;
    }

    private int findIndexOfProductInCart(List<OrderItem> orderItems, Phone phone){
        int index = 0;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getPhone().equals(phone)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private void updateCartTotalPriceAndQuantity(Cart cart){
        BigDecimal newTotalPrice = computeCartTotalPrice(cart);
        Long newTotalQuantity = computeCartTotalQuantity(cart);
        cart.setTotalPrice(newTotalPrice);
        cart.setTotalQuantity(newTotalQuantity);
    }

    private BigDecimal computeCartTotalPrice(Cart cart){
        return cart.getOrderItems()
                .stream()
                .map(item -> item.getPhone().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.valueOf(0), BigDecimal::add);
    }

    private Long computeCartTotalQuantity(Cart cart){
        return cart.getOrderItems()
                .stream()
                .mapToLong(item -> item.getQuantity())
                .sum();
    }


    public void setCart(Cart cart) {
        this.cart = cart;
    }



}
