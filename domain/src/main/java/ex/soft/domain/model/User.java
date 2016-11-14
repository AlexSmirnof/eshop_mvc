package ex.soft.domain.model;

/**
 * Created by Alex108 on 19.10.2016.
 */
public class User {

    private Long key;
    private String firstName;
    private String lastName;
    private String deliveryAddress;
    private String contactPhoneNo;
    private String login;
    private String password;

    public User() {}

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "User{" +
                "key=" + key +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", contactPhoneNo='" + contactPhoneNo + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
