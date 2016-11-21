package ex.soft.domain.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Alex108 on 19.10.2016.
 */
@Component("anonymous")
@PropertySource("classpath:messages/user.properties")
public class User {

    @Value("${anonymous.key}")
    private Long key;
    @Value("${anonymous.firstName}")
    private String firstName;
    @Value("${anonymous.lastName}")
    private String lastName;
    @Value("${anonymous.login}")
    private String login;
    @Value("${anonymous.password}")
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

    @Override
    public String toString() {
        return "User{key=" + key +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
