package ex.soft.domain.dao.jdbc;

import ex.soft.domain.dao.ProductDao;
import ex.soft.domain.model.Phone;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alex108 on 11.10.2016.
 */
@Repository("phoneDao")
public class JdbcPhoneDao implements ProductDao<Phone> {

    public static final String INSERT = "INSERT INTO Phones (model, color, displaySize, ph_length, width, camera, price) VALUES (?,?,?,?,?,?,?)";
    public static final String UPDATE = "UPDATE Phones SET model=?, color=?, displaySize=?, ph_length=?, width=?, camera=?, price=? WHERE id=?";
    public static final String GET = "SELECT id, model, color, displaySize, ph_length, width, camera, price FROM Phones WHERE id=?";
    public static final String FIND_ALL = "SELECT id, model, color, displaySize, ph_length, width, camera, price FROM Phones";

    private JdbcTemplate jdbcTemplate;

    public JdbcPhoneDao(){}

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Phone get(Long key){
        return jdbcTemplate.query(GET, new Object[]{key}, rs -> {
            if (rs.next()) {
                Phone phone = new Phone();
                return setPhoneProperties(phone, rs);
            }
            return null;
        });
    }

    @Override
    public void save(Phone phone) {
        Long key = phone.getKey();
        if (key != null){
            jdbcTemplate.update(UPDATE, ps -> setQueryValues(phone, ps, key));
        } else {
            jdbcTemplate.update(INSERT, ps -> setQueryValues(phone, ps, null));
        }
    }

    @Override
    public List<Phone> findAll() {
        return jdbcTemplate.query(FIND_ALL,(rs, row) -> {
            Phone phone = new Phone();
            return setPhoneProperties(phone, rs);
        });
    }

    @Override
    public void close() {}

    private static Phone setPhoneProperties(Phone phone, ResultSet rs) throws SQLException {
        phone.setKey(rs.getLong("id"));
        phone.setModel(rs.getString("model"));
        phone.setColor(rs.getString("color"));
        phone.setDisplaySize(rs.getString("displaySize"));
        phone.setLength(rs.getString("ph_length"));
        phone.setWidth(rs.getString("width"));
        phone.setCamera(rs.getString("camera"));
        phone.setPrice(rs.getBigDecimal("price"));
        return phone;
    }

    private static void setQueryValues(Phone phone, PreparedStatement ps, Long key) throws SQLException {
        int i = 1;
        ps.setString(i++, phone.getModel());
        ps.setString(i++, phone.getColor());
        ps.setString(i++, phone.getDisplaySize());
        ps.setString(i++, phone.getLength());
        ps.setString(i++, phone.getWidth());
        ps.setString(i++, phone.getCamera());
        ps.setBigDecimal(i++, phone.getPrice());
        if ( key != null ) ps.setLong(i++, phone.getKey());
    }
}
