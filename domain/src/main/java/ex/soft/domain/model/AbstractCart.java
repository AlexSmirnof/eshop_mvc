package ex.soft.domain.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex108 on 05.11.2016.
 */
@Component
public abstract class AbstractCart{

    private Long totalQuantity;
    private BigDecimal totalPrice;
    private List<OrderItem> orderItems;

    public AbstractCart() {
        totalQuantity = 0L;
        totalPrice = BigDecimal.ZERO;
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
    public String toString() {
        return  "totalQuantity=" + totalQuantity +
                ", totalPrice=" + totalPrice +
                ", orderItems=" + orderItems;
    }
}
