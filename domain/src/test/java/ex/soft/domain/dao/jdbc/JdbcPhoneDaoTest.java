package ex.soft.domain.dao.jdbc;

import ex.soft.domain.model.Phone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex108 on 13.10.2016.
 */
@RunWith(Parameterized.class)
public class JdbcPhoneDaoTest {

    private EmbeddedDatabase db;
    private JdbcPhoneDao phoneDao;

    @Parameterized.Parameters
    public static Collection<Phone[]> data(){
        return Arrays.asList( new Phone[][]{
                {
                        new Phone(null, "Samsung Galaxy S6 16GB", "black", "4\"", new BigDecimal("250.00")),
                        new Phone(null, "Samsung Galaxy S6 32GB", "black", "4\"", new BigDecimal("300.00")),
                        new Phone(null, "Samsung Galaxy S6 16GB", "black", "4\"", new BigDecimal("250.00")),
                        new Phone(null, "Samsung Galaxy S6 32GB", "black", "4\"", new BigDecimal("300.00")),
                }
        });
    }

    @Parameterized.Parameter
    public Phone input;
    @Parameterized.Parameter(value = 1)
    public Phone input2;
    @Parameterized.Parameter(value = 2)
    public Phone expected;
    @Parameterized.Parameter(value = 3)
    public Phone expected2;


    @Before
    public void setUp() throws Exception {
        db = new EmbeddedDatabaseBuilder()
                .setName("Phone Dao Test")
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("/db/schema.sql")
//                .addScript("/db/test-data.sql")
                .build();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(db);
        phoneDao = new JdbcPhoneDao();
        phoneDao.setDataSource(db);
    }

    @After
    public void tearDown() throws Exception {
        db.shutdown();
    }

    @Test
    public void get() throws Exception {
        phoneDao.save(input);
        Phone phone = phoneDao.get(1l);
        assertEquals(expected.getModel(), phone.getModel());
        assertEquals(expected.getPrice(), phone.getPrice());
    }

    @Test
    public void save() throws Exception {
        phoneDao.save(input);
        Phone phone = phoneDao.findAll().get(0);
        assertEquals(expected.getModel(), phone.getModel());
        assertEquals(expected.getPrice(), phone.getPrice());
    }

    @Test
    public void findAll() throws Exception {
        phoneDao.save(input);
        phoneDao.save(input2);
        Phone phone1 =  phoneDao.findAll().get(0);
        Phone phone2 =  phoneDao.findAll().get(1);
        assertEquals(expected.getModel(), phone1.getModel());
        assertEquals(expected.getPrice(), phone1.getPrice());
        assertEquals(expected2.getModel(), phone2.getModel());
        assertEquals(expected2.getPrice(), phone2.getPrice());
    }
}