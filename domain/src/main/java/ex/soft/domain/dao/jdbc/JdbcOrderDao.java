package ex.soft.domain.dao.jdbc;

import ex.soft.domain.dao.OrderDao;
import ex.soft.domain.dao.PhoneDao;
import ex.soft.domain.model.Order;
import ex.soft.domain.model.OrderItem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alex108 on 11.10.2016.
 */
@Repository("orderDao")
public class JdbcOrderDao implements OrderDao {

    public static final String FIND_ALL = "SELECT id, firstName, lastName, deliveryAddress, contactPhoneNo, totalPrice FROM Orders";
    public static final String GET =      "SELECT id, firstName, lastName, deliveryAddress, contactPhoneNo, totalPrice FROM Orders WHERE id = :key";
    public static final String INSERT =   "INSERT INTO Orders (firstName, lastName, deliveryAddress, contactPhoneNo, totalPrice) " +
                                                     "VALUES (:firstName, :lastName, :deliveryAddress, :contactPhoneNo, :totalPrice)";
    public static final String UPDATE =   "UPDATE Orders SET firstName = :firstName, lastName = :lastName, deliveryAddress = :deliveryAddress, " +
                                                            "contactPhoneNo = :contactPhoneNo, totalPrice = :totalPrice WHERE id = :key";
    public static final String FIND_ALL_ORDER_ITEMS_BY_KEY = "SELECT phone_id, quantity FROM OrderItems WHERE order_id = :order_id";
    public static final String GET_ORDER_ITEM_BY_KEY =       "SELECT phone_id, quantity FROM OrderItems WHERE order_id = :key";
    public static final String INSERT_ORDER_ITEM =           "INSERT INTO OrderItems (phone_id, quantity, order_id) VALUES (:phone_id, :quantity, :order_id)";
    public static final String UPDATE_ORDER_ITEM =           "UPDATE OrderItems SET phone_id = :phone_id, quantity = :quantity WHERE order_id = :order_id";

    private NamedParameterJdbcTemplate jdbcTemplate;
    private PhoneDao phoneDao;

    public JdbcOrderDao() {}

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    }

    @Resource(name = "phoneDao")
    public void setPhoneDao(PhoneDao phoneDao) {
        this.phoneDao = phoneDao;
    }

    @Override
    public Order get(Long key) {
        SqlParameterSource paramSource = new MapSqlParameterSource("key", key);
        Order order = jdbcTemplate.query(GET, paramSource, rs -> {
            if (rs.next()){
                Order rsOrder = new Order();
                return setOrderProperties(rsOrder, rs);
            }
            return null;
        });
        if (order == null ) return null;
        List<OrderItem> orderItems = jdbcTemplate.query(GET_ORDER_ITEM_BY_KEY, paramSource, (rs, row) -> {
            OrderItem orderItem = new OrderItem();
            return setOrderItemProperties(orderItem, rs, phoneDao);
        });
        order.setOrderItems(orderItems);
        return order;
    }

    @Override
    public void save(Order order) {
        Long key = order.getKey();
        if ( key == null){
            SqlParameterSource paramSource = setOrderValues(order, new MapSqlParameterSource(), null);
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(INSERT, paramSource, keyHolder);
            for (OrderItem orderItem: order.getOrderItems()){
                SqlParameterSource paramSourceOrderItem = setOrderItemValues(orderItem, new MapSqlParameterSource(), (Long) keyHolder.getKey());
                jdbcTemplate.update(INSERT_ORDER_ITEM, paramSourceOrderItem);
            }
        } else {
            SqlParameterSource paramSource = setOrderValues(order, new MapSqlParameterSource(), key);
            jdbcTemplate.update(UPDATE, paramSource);
            for (OrderItem orderItem: order.getOrderItems()){
                SqlParameterSource paramSourceOrderItem = setOrderItemValues(orderItem, new MapSqlParameterSource(), key);
                jdbcTemplate.update(UPDATE_ORDER_ITEM, paramSourceOrderItem);
            }
        }
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = jdbcTemplate.query(FIND_ALL, (rs, row) -> {
            Order order = new Order();
            return setOrderProperties(order, rs);
        });
        for (Order order : orders) {
            SqlParameterSource paramSource = new MapSqlParameterSource("order_id", order.getKey());
            List<OrderItem> orderItems = jdbcTemplate.query(FIND_ALL_ORDER_ITEMS_BY_KEY, paramSource, (rs, row) -> {
                OrderItem orderItem = new OrderItem();
                return setOrderItemProperties(orderItem, rs, phoneDao);
            });
            order.setOrderItems(orderItems);
        }
        return orders;
    }

    @Override
    public void close() {

    }

    private static Order setOrderProperties(Order order, ResultSet rs) throws SQLException {
        order.setKey(rs.getLong("id"));
        order.setFirstName(rs.getString("firstName"));
        order.setLastName(rs.getString("lastName"));
        order.setDeliveryAddress(rs.getString("deliveryAddress"));
        order.setContactPhoneNo(rs.getString("contactPhoneNo"));
        order.setTotalPrice(rs.getBigDecimal("totalPrice"));
        return order;
    }
    private static OrderItem setOrderItemProperties(OrderItem orderItem, ResultSet rs, PhoneDao phoneDao) throws SQLException {
        Long phone_id = rs.getLong("phone_id");
        orderItem.setPhone(phoneDao.get(phone_id));
        orderItem.setQuantity(rs.getLong("quantity"));
        return orderItem;
    }

    private static SqlParameterSource setOrderValues(Order order, MapSqlParameterSource paramSource, Long key){
        paramSource = paramSource.addValue("firstName", order.getFirstName())
                                 .addValue("lastName", order.getLastName())
                                 .addValue("deliveryAddress", order.getDeliveryAddress())
                                 .addValue("contactPhoneNo", order.getContactPhoneNo())
                                 .addValue("totalPrice", order.getTotalPrice());
        return key == null ? paramSource : paramSource.addValue("key", key);

    }
    private static SqlParameterSource setOrderItemValues(OrderItem orderItem, MapSqlParameterSource paramSource, Long key){
        paramSource = paramSource.addValue("phone_id", orderItem.getPhone().getKey())
                                 .addValue("quantity", orderItem.getQuantity())
                                 .addValue("order_id", key);
        return paramSource;
    }

}
