package ex.soft.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex108 on 05.11.2016.
 */
public abstract class AbstractCart<T extends AbstractCart> {

    private Long totalQuantity;
    private BigDecimal totalPrice;
    private List<OrderItem> orderItems;

    public AbstractCart() {
        totalQuantity = Long.valueOf(0L);
        totalPrice = BigDecimal.valueOf(0);
        orderItems = new ArrayList<>();
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

        T that = (T) o;

        return orderItems != null ? orderItems.equals(that.getOrderItems()) : (that.getOrderItems() == null ? true : false);
    }

    @Override
    public int hashCode() {
        return getOrderItems().hashCode();
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
