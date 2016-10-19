package ex.soft.domain.dao.jdbc;

import ex.soft.domain.dao.OrderDao;
import ex.soft.domain.dao.PhoneDao;
import ex.soft.domain.model.Order;
import ex.soft.domain.model.OrderItem;
import ex.soft.domain.model.Phone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex108 on 14.10.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-domain-test.xml", "classpath:db-config-order-test.xml"})
public class JdbcOrderDaoTest {

    @Autowired
    private OrderDao orderDao;
    public Phone phoneKey1 = new Phone(1l, "Samsung Galaxy S6 16GB", "black", "4\"","14mm","56mm","12MP", new BigDecimal("250.00"));
    public Phone phoneKey2 = new Phone(2l, "Samsung Galaxy S6 32GB", "black", "4\"","14mm","56mm","12MP", new BigDecimal("300.00"));
    public Order input = new Order(null, "Alex", "XXX", "YYY", "12345", new BigDecimal("250.00"), Arrays.asList(new OrderItem(phoneKey1, 1l)));
    public Order input2 = new Order(null, "Ann", "XXX", "YYY", "12345", new BigDecimal("600.00"), Arrays.asList(new OrderItem(phoneKey2, 2l)));
    public Order expected = new Order(null, "Alex", "XXX", "YYY", "12345", new BigDecimal("250.00"), Arrays.asList(new OrderItem(phoneKey1, 1l)));
    public Order expected2 = new Order(null, "Ann", "XXX", "YYY", "12345", new BigDecimal("600.00"), Arrays.asList(new OrderItem(phoneKey2, 2l)));


    @Test
    public void get() throws Exception {
        orderDao.save(input);
        Order order = orderDao.get(1l);
        assertEquals(expected.getFirstName(), order.getFirstName());
        assertEquals(expected.getLastName(), order.getLastName());
        assertEquals(expected.getTotalPrice(), order.getTotalPrice());
        assertEquals(expected.getOrderItems().get(0).getQuantity(), order.getOrderItems().get(0).getQuantity());
        assertEquals(expected.getOrderItems().get(0).getPhone().getModel(), order.getOrderItems().get(0).getPhone().getModel());
        assertEquals(expected.getOrderItems().get(0).getPhone().getPrice(), order.getOrderItems().get(0).getPhone().getPrice());
    }

    @Test
    public void save() throws Exception {
        orderDao.save(input);
        Order order = orderDao.findAll().get(0);
        assertEquals(expected.getFirstName(), order.getFirstName());
        assertEquals(expected.getLastName(), order.getLastName());
        assertEquals(expected.getTotalPrice(), order.getTotalPrice());
        assertEquals(expected.getOrderItems().get(0).getQuantity(), order.getOrderItems().get(0).getQuantity());
        assertEquals(expected.getOrderItems().get(0).getPhone().getModel(), order.getOrderItems().get(0).getPhone().getModel());
        assertEquals(expected.getOrderItems().get(0).getPhone().getPrice(), order.getOrderItems().get(0).getPhone().getPrice());
    }

    @Test
    public void findAll() throws Exception {
        orderDao.save(input);
        orderDao.save(input2);
        Order order = orderDao.findAll().get(0);
        Order order2 = orderDao.findAll().get(1);
        assertEquals(expected.getFirstName(), order.getFirstName());
        assertEquals(expected.getLastName(), order.getLastName());
        assertEquals(expected.getTotalPrice(), order.getTotalPrice());
        assertEquals(expected.getOrderItems().get(0).getQuantity(), order.getOrderItems().get(0).getQuantity());
        assertEquals(expected.getOrderItems().get(0).getPhone().getModel(), order.getOrderItems().get(0).getPhone().getModel());
        assertEquals(expected.getOrderItems().get(0).getPhone().getPrice(), order.getOrderItems().get(0).getPhone().getPrice());
        assertEquals(expected2.getFirstName(), order2.getFirstName());
        assertEquals(expected2.getLastName(), order2.getLastName());
        assertEquals(expected2.getTotalPrice(), order2.getTotalPrice());
        assertEquals(expected2.getOrderItems().get(0).getQuantity(), order2.getOrderItems().get(0).getQuantity());
        assertEquals(expected2.getOrderItems().get(0).getPhone().getModel(), order2.getOrderItems().get(0).getPhone().getModel());
        assertEquals(expected2.getOrderItems().get(0).getPhone().getPrice(), order2.getOrderItems().get(0).getPhone().getPrice());

    }
}