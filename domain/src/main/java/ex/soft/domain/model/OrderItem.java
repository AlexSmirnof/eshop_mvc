package ex.soft.domain.model;

/**
 * Created by Alex108 on 11.10.2016.
 */
public class OrderItem {

    private Phone phone;
    private Long quantity;

    public OrderItem() {}

    public OrderItem(Phone phone, Long quantity) {
        this.phone = phone;
        this.quantity = quantity;
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
