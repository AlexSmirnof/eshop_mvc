package ex.soft.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex108 on 19.10.2016.
 */
public class Cart {

    private Long totalQuantity;
    private BigDecimal totalPrice;
    private List<OrderItem> orderItems;

    public Cart() {
        totalQuantity = Long.valueOf(0L);
        totalPrice = BigDecimal.valueOf(0);
        orderItems = new ArrayList<>();
    }

    public Cart(Long totalQuantity, BigDecimal totalPrice, List<OrderItem> orderItems) {
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart that = (Cart) o;

        return orderItems != null ? orderItems.equals(that.orderItems) : (that.getOrderItems() == null ? true : false);
    }

    @Override
    public int hashCode() {
        return orderItems.hashCode();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalQuantity=" + totalQuantity +
                ", totalPrice=" + totalPrice +
                ", orderItems=" + orderItems +
                '}';
    }
}