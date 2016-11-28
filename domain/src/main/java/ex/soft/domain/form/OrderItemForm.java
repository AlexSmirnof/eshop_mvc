package ex.soft.domain.form;

import ex.soft.domain.model.Phone;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by Alex108 on 25.11.2016.
 */
public class OrderItemForm {

    private Phone phone;

    @NotNull(message = "Please supply product quantity.")
    @Min(value = 1, message = "Quantity must be greater than 0.")
    private Long quantity;

    public OrderItemForm() {
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
    public String toString() {
        return "OrderItemForm{" +
                "phone=" + phone +
                ", quantity=" + quantity +
                '}';
    }
}
