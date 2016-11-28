package ex.soft.domain.form;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Alex108 on 24.11.2016.
 */
public class OrderForm {

    private Long totalQuantity;
    private BigDecimal totalPrice;
    @Valid
    private List<OrderItemForm> orderItemForms;

    @NotBlank(message = "Please supply First Name.")
    @Size(min = 2, message = "First Name can contain from 2 to 20 characters.")
    private String firstName;

    @NotBlank(message = "Please supply Last Name")
    @Size(min = 2, message = "Last Name can contain from 2 to 20 characters.")
    private String lastName;

    @NotBlank(message = "Please supply Delivery Address.")
    @Size(min=2, max=250, message = "Delivery Address can contain from 2 to 250 characters.")
    private String deliveryAddress;

    @NotBlank(message = "Please supply a Phone Number.")
    @Pattern(regexp="\\d{10}", message = "Phone Number can contain only 10 digits.")
    private String contactPhoneNo;

    @Size(max=250, message = "Description can contain less than 250 characters.")
    private String description;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getContactPhoneNo() {
        return contactPhoneNo;
    }

    public void setContactPhoneNo(String contactPhoneNo) {
        this.contactPhoneNo = contactPhoneNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "OrderForm{" +
                "totalQuantity=" + totalQuantity +
                ", totalPrice=" + totalPrice +
                ", orderItemForms=" + orderItemForms +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", contactPhoneNo='" + contactPhoneNo + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
