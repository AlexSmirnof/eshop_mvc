package ex.soft.service.manager;

import ex.soft.domain.dao.DaoFactory;
import ex.soft.domain.dao.OrderDao;
import ex.soft.domain.model.Order;
import ex.soft.domain.model.Phone;
import ex.soft.service.transaction.ITransactional;
import ex.soft.service.transaction.TransactionManager;

import java.util.List;

/**
 * Created by Alex108 on 12.10.2016.
 */
public class OrderService implements IService<Order> {

    private OrderDao orderDao;
    private ITransactional txManager;

    private OrderService(){
        orderDao = DaoFactory.getInstance().getOrderDao();
        txManager = TransactionManager.getInstance();
    }

    public static OrderService getInstance(){
        return OrderServiceHolder.INSTANCE;
    }
    @Override
    public Order get(Long key) {
        return txManager.<Order>doInTransaction(() -> orderDao.get(key));
    }

    @Override
    public void save(Order order) {
        txManager.<Void>doInTransaction(()-> {
            orderDao.save(order);
            return null;
        });
    }

    @Override
    public List<Order> findAll() {
        return txManager.<List<Order>>doInTransaction(() -> orderDao.findAll());
    }

    @Override
    public void close() {
        orderDao.findAll();
    }

    private static class OrderServiceHolder{
        private static final OrderService INSTANCE = new OrderService();
        private OrderServiceHolder() {
        }
    }
}
