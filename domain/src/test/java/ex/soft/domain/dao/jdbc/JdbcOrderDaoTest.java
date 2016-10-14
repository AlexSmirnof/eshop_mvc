package ex.soft.domain.dao.jdbc;

import ex.soft.domain.model.Order;
import ex.soft.domain.model.OrderItem;
import ex.soft.domain.model.Phone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Suite;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex108 on 14.10.2016.
 */

@RunWith(Parameterized.class)
public class JdbcOrderDaoTest {

    private EmbeddedDatabase db;
    private JdbcOrderDao orderDao;

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        Phone phoneKey1 = new Phone(1l, "Samsung Galaxy S6 16GB", "black", "4\"", new BigDecimal("250.00"));
        Phone phoneKey2 = new Phone(2l, "Samsung Galaxy S6 32GB", "black", "4\"", new BigDecimal("300.00"));
        OrderItem orderItem1 = new OrderItem(phoneKey1, 1l);
        OrderItem orderItem2 = new OrderItem(phoneKey2, 2l);
        return Arrays.asList(new Object[][]{
                {
                        new Phone(null, "Samsung Galaxy S6 16GB", "black", "4\"", new BigDecimal("250.00")),
                        new Phone(null, "Samsung Galaxy S6 32GB", "black", "4\"", new BigDecimal("300.00")),
                        new Order(null, "Alex", "XXX", "YYY", "12345", new BigDecimal("250.00"), Arrays.asList(orderItem1)),
                        new Order(null, "Ann", "XXX", "YYY", "12345", new BigDecimal("600.00"), Arrays.asList(orderItem2)),
                        new Order(null, "Alex", "XXX", "YYY", "12345", new BigDecimal("250.00"), Arrays.asList(orderItem1)),
                        new Order(null, "Ann", "XXX", "YYY", "12345", new BigDecimal("600.00"), Arrays.asList(orderItem2)),
                }
        });
    }

    @Parameterized.Parameter
    public Phone phone1;
    @Parameterized.Parameter(value = 1)
    public Phone phone2;
    @Parameterized.Parameter(value = 2)
    public Order input;
    @Parameterized.Parameter(value = 3)
    public Order input2;
    @Parameterized.Parameter(value = 4)
    public Order expected;
    @Parameterized.Parameter(value = 5)
    public Order expected2;

    @Before
    public void setUp() throws Exception {
        db = new EmbeddedDatabaseBuilder()
                .setName("OrderDao Test")
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("/db/schema.sql")
//                .addScript("/db/test-data.sql")
                .build();
//
        JdbcPhoneDao phoneDao = new JdbcPhoneDao();
        phoneDao.setDataSource(db);
        phoneDao.save(phone1);
        phoneDao.save(phone2);
//
        orderDao = new JdbcOrderDao();
        orderDao.setDataSource(db);
        orderDao.setPhoneDao(phoneDao);
    }

    @After
    public void tearDown() throws Exception {
        db.shutdown();
    }

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