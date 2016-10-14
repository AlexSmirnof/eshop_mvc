package ex.soft.domain.dao.jdbc;

import ex.soft.domain.dao.PhoneDao;
import ex.soft.domain.model.Phone;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Alex108 on 11.10.2016.
 */
public class JdbcPhoneDao implements PhoneDao {

    public static final String INSERT = "INSERT INTO Phones (model, color, displaySize, price) VALUES (?,?,?,?)";
    public static final String UPDATE = "UPDATE Phones SET model=?, color=?, displaySize=?, price=? WHERE id=?";
    public static final String GET = "SELECT id, model, color, displaySize, price FROM Phones WHERE id=?";
    public static final String FIND_ALL = "SELECT id, model, color, displaySize, price FROM Phones";

    private JdbcTemplate jdbcTemplate;

    public JdbcPhoneDao(){}

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Phone get(Long key){
        return jdbcTemplate.query(GET, new Object[]{key}, rs -> {
            if (rs.next()) {
                Phone phone = new Phone();
                phone.setKey(rs.getLong("id"));
                phone.setModel(rs.getString("model"));
                phone.setColor(rs.getString("color"));
                phone.setDisplaySize(rs.getString("displaySize"));
                phone.setPrice(rs.getBigDecimal("price"));
                return phone;
            }
            return null;
        });
    }

    @Override
    public void save(Phone phone) {
        if (phone.getKey() != null){
            jdbcTemplate.update(UPDATE, phone.getModel(),phone.getColor(),phone.getDisplaySize(),phone.getPrice(),phone.getKey());
        } else {
            jdbcTemplate.update(INSERT, phone.getModel(),phone.getColor(),phone.getDisplaySize(),phone.getPrice());
        }
    }

    @Override
    public List<Phone> findAll() {
        return jdbcTemplate.query(FIND_ALL,(rs, row) -> {
            Phone phone = new Phone();
            phone.setKey(rs.getLong("id"));
            phone.setModel(rs.getString("model"));
            phone.setColor(rs.getString("color"));
            phone.setDisplaySize(rs.getString("displaySize"));
            phone.setPrice(rs.getBigDecimal("price"));
            return phone;
        });
    }

    @Override
    public void close() {}

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext-domain.xml","db-config.xml"});

        PhoneDao phoneDao = (PhoneDao) context.getBean("phoneDao");
        Phone phone = new Phone(1L, "SONY", "white","8\"",new BigDecimal("55.55"));
        phoneDao.save(phone);
        phoneDao.findAll().forEach(System.out::println);
        System.out.println("GET");
//        System.out.println(phoneDao.get(5l));

    }

}
