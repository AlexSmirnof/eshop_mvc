package ex.soft.domain.dao;

import ex.soft.domain.model.Order;

import java.util.List;

/**
 * Created by Alex108 on 11.10.2016.
 */
public interface OrderDao{

    Order get(Long key);

    Long save(Order order);

    List<Order> findAll();

    void close();
}
