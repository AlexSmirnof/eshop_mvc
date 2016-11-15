package ex.soft.domain.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Alex108 on 11.10.2016.
 */
public class Order extends AbstractCart{

    private Long key;

    @NotNull(message = "Fist Name can not be empty")
    @Size(min = 2)
    private String firstName;

    @NotNull(message = "Last Name can not be empty")
    @Size(min = 2)
    private String lastName;

    @NotNull(message = "Address can not be empty")
    @Size(min = 5)
    private String deliveryAddress;

    @NotNull(message = "PhoneNo can not be empty")
    @Size(min = 7)
    private String contactPhoneNo;
    private String description;
    private Long userId;

    public Order() {}

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "key=" + key +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", contactPhoneNo='" + contactPhoneNo + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId + "\', " +
                 super.toString() + '}';
    }
}
