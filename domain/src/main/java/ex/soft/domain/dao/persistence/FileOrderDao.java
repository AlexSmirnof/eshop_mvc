package ex.soft.domain.dao.persistence;

import ex.soft.domain.dao.OrderDao;
import ex.soft.domain.model.Order;
import ex.soft.domain.model.OrderItem;
import ex.soft.domain.model.Phone;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;

/**
 * Created by Alex108 on 11.10.2016.
 */
public class FileOrderDao implements OrderDao {

    private static final String FILE = "orders.xml";

    private FileOrderDao() {
        init();
    }

    private void init(){
        XMLEncoder encoder = null;
        try{
            new File(FILE).createNewFile();
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(FILE)));
            encoder.setExceptionListener( e -> System.out.println(e.toString()));
            encoder.writeObject(new LinkedHashMap<>());
            encoder.flush();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
                if (encoder != null) {
                    encoder.close();
                }
        }
    }

    public static FileOrderDao getInstance(){
        return FileOrderDaoHolder.INSTANCE;
    }

    private synchronized void writeOrderToFile(Map<Long, Order> newOrders, String file){
        Map<Long, Order> orders = readOrderFromFile(FILE);
        orders.putAll(newOrders);
        XMLEncoder encoder = null;
        try {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
            encoder.setExceptionListener( e -> System.out.println(e.toString()));
            encoder.writeObject(orders);
            encoder.flush();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } finally {
            if (encoder != null) {
                encoder.close();
            }
        }
    }

    private synchronized Map<Long, Order> readOrderFromFile(String file){
        XMLDecoder decoder = null;
        Map<Long, Order> orders = null;
        try{
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
            orders = (Map<Long, Order>) decoder.readObject();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } finally {
            if (decoder != null) {
                decoder.close();
            }
        }
        return orders;
    }

    @Override
    public Order get(Long key) {
        return readOrderFromFile(FILE).get(key);
    }

    @Override
    public void save(Order order) {
        writeOrderToFile(Collections.singletonMap(order.getKey(), order), FILE);
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(readOrderFromFile(FILE).values());
    }

    @Override
    public void close() {
        /*
            NOP
         */
    }

    private static class FileOrderDaoHolder{
        private static final FileOrderDao INSTANCE = new FileOrderDao();

        private FileOrderDaoHolder() {}
    }

    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.setModel("Samsung Galaxy S6 16GB");

        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList();
        orderItems.add(new OrderItem(phone, 1l));
        order.setOrderItems(orderItems);

        OrderDao orderDao = new FileOrderDao();
        orderDao.save(order);
        System.out.println(phone.getModel().equals(orderDao.findAll().get(0).getOrderItems().get(0).getPhone().getModel()));


    }
}
