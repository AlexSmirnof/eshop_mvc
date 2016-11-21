package ex.soft.domain.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by Alex108 on 11.10.2016.
 */
@Component
public class OrderItem {

    private Phone phone;

    @NotNull(message = "Quantity can not be empty")
    @Min(value = 1, message = "Quantity must be positive number")
    private Long quantity;

    public OrderItem() {
        quantity = 0L;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem that = (OrderItem) o;

        if (phone != null ? !phone.equals(that.phone) : true) return false;
        return quantity != null ? quantity.equals(that.quantity) : false;

    }

    @Override
    public int hashCode() {
        int result = phone != null ? phone.hashCode() : 0;
        result = result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "phone=" + phone +
                ", quantity=" + quantity +
                '}';
    }
}
