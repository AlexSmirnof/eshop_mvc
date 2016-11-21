package ex.soft.domain.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Alex108 on 11.10.2016.
 */
@Component
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
//    @Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}")
    private String contactPhoneNo;

    @Size(min=2, max=250)
    private String description;

    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{key=" + key +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", contactPhoneNo='" + contactPhoneNo + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + user + "\', " +
                 super.toString() + '}';
    }
}
