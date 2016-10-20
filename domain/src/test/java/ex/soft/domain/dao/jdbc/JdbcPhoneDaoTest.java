package ex.soft.domain.dao.jdbc;

import ex.soft.domain.dao.ProductDao;
import ex.soft.domain.model.Phone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex108 on 13.10.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-domain-test.xml", "classpath:db-config-phone-test.xml"})
public class JdbcPhoneDaoTest{

    @Autowired
    private ProductDao<Phone> productDao;

    public Phone input = new Phone(null, "Samsung Galaxy S6 16GB", "black", "4\"", "14mm","56mm","12MP",new BigDecimal("250.00"));
    public Phone input2 = new Phone(null, "Samsung Galaxy S6 32GB", "black", "4\"","14mm","56mm","12MP", new BigDecimal("300.00"));
    public Phone expected = new Phone(null, "Samsung Galaxy S6 16GB", "black", "4\"", "14mm","56mm","12MP", new BigDecimal("250.00"));
    public Phone expected2 = new Phone(null, "Samsung Galaxy S6 32GB", "black", "4\"", "14mm","56mm","12MP", new BigDecimal("300.00"));

    @Test
    public void get() throws Exception {
        productDao.save(input);
        Phone phone = productDao.get(1l);
        assertEquals(expected.getModel(), phone.getModel());
        assertEquals(expected.getPrice(), phone.getPrice());
    }

    @Test
    public void save() throws Exception {
        productDao.save(input);
        Phone phone = productDao.findAll().get(0);
        assertEquals(expected.getModel(), phone.getModel());
        assertEquals(expected.getPrice(), phone.getPrice());
    }

    @Test
    public void findAll() throws Exception {
        productDao.save(input);
        productDao.save(input2);
        Phone phone1 =  productDao.findAll().get(0);
        Phone phone2 =  productDao.findAll().get(1);
        assertEquals(expected.getModel(), phone1.getModel());
        assertEquals(expected.getPrice(), phone1.getPrice());
        assertEquals(expected2.getModel(), phone2.getModel());
        assertEquals(expected2.getPrice(), phone2.getPrice());
    }
}