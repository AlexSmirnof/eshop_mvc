package ex.soft.domain.dao.jdbc;

import ex.soft.domain.dao.UserDao;
import ex.soft.domain.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by Alex108 on 19.10.2016.
 */
@Repository("userDao")
public class JdbcUserDao implements UserDao {

    public static final String INSERT = "INSERT INTO Users (firstName, lastName, deliveryAddress, contactPhoneNo, login, password) VALUES (?,?,?,?,?,?)";
    public static final String UPDATE = "UPDATE Users SET firstName=?, lastName=?, deliveryAddress=?, contactPhoneNo=?, login=?, password=?, WHERE id=?";
    public static final String GET = "SELECT id, firstName, lastName, deliveryAddress, contactPhoneNo, login, password FROM Users WHERE id=?";
    public static final String DELETE = "DELETE FROM Users WHERE id=?";

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(){}

    @Override
    public User get(Long key) {
        return jdbcTemplate.query(GET, new Object[]{key}, new int[]{Types.BIGINT}, rs ->{
            if (rs.next()){
                final User user = new User();
                return setUserProperties(user,rs);
            }
            return null;
        });
    }

    @Override
    public Long save(User user) {
        final Long key = user.getKey();
        if (key != null){
            jdbcTemplate.update(UPDATE, ps -> setQueryValues(user, ps, key));
            return key;
        } else {
            final KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
//                PreparedStatement ps = connection.prepareStatement(INSERT, new String[]{"id"});
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                setQueryValues(user, ps, null);
                return ps;
            }, keyHolder);
            return keyHolder.getKey().longValue();
        }
    }

    @Override
    public void delete(Long key) {
        jdbcTemplate.update(DELETE, new Object[]{key}, new int[]{Types.BIGINT});
    }

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static User setUserProperties(User user, ResultSet rs) throws SQLException {
        user.setKey(rs.getLong("id"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setDeliveryAddress(rs.getString("deliveryAddress"));
        user.setContactPhoneNo(rs.getString("contactPhoneNo"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        return user;
    }

    private static void setQueryValues(User user, PreparedStatement ps, Long key) throws SQLException {
        int i = 1;
        ps.setString(i++, user.getFirstName());
        ps.setString(i++, user.getLastName());
        ps.setString(i++, user.getDeliveryAddress());
        ps.setString(i++, user.getContactPhoneNo());
        ps.setString(i++, user.getLogin());
        ps.setString(i++, user.getPassword());
        if (key != null) ps.setLong(i, key);

    }
}
