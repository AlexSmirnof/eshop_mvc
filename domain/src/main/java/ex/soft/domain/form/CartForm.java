package ex.soft.domain.form;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex108 on 24.11.2016.
 */
public class CartForm {

    private Long totalQuantity;
    private BigDecimal totalPrice;
    @Valid
    private List<OrderItemForm> orderItemForms;

    public CartForm() {
        totalQuantity = 0L;
        totalPrice = BigDecimal.ZERO;
        orderItemForms = new ArrayList<>();
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

    public List<OrderItemForm> getOrderItemForms() {
        return orderItemForms;
    }

    public void setOrderItemForms(List<OrderItemForm> orderItemForms) {
        this.orderItemForms = orderItemForms;
    }

    @Override
    public String toString() {
        return "CartForm{" +
                "totalQuantity=" + totalQuantity +
                ", totalPrice=" + totalPrice +
                ", orderItemForms=" + orderItemForms +
                '}';
    }

}
