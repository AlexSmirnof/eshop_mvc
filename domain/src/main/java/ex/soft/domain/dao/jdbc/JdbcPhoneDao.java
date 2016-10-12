package ex.soft.domain.dao.jdbc;

import ex.soft.domain.dao.PhoneDao;
import ex.soft.domain.datasource.DataSource;
import ex.soft.domain.model.Phone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex108 on 11.10.2016.
 */
public class JdbcPhoneDao implements PhoneDao {

    private static final String TABLE_NAME = "Phones";
    private static final String KEY_COL = "id";
    private static final String MODEL_COL = "model";
    private static final String PRICE_COL = "price";
    private static final String VALUES_STRING = "'%s','%s','%s'";
    private final DataSource dataSource;

    private JdbcPhoneDao() {
        this.dataSource = DataSource.getInstance();
    }

    public static PhoneDao getInstance(){
        return JdbcPhoneDaoHolder.INSTANCE;
    }

    @Override
    public Phone get(Long key){
        String query = String.format(Executor.GET, TABLE_NAME, key.longValue());
        return new Executor(dataSource.getConnection()).execQuery(query, rs -> {
            rs.next();
            return new Phone(rs.getLong(KEY_COL),rs.getString(MODEL_COL),rs.getBigDecimal(PRICE_COL));
        } );
    }

    @Override
    public void save(Phone phone) {
        String update = String.format(VALUES_STRING, phone.getKey(), phone.getModel(), phone.getPrice());
        update = String.format(Executor.SAVE, TABLE_NAME, update);
        new Executor(dataSource.getConnection()).execUpdate(update);
    }

    @Override
    public List<Phone> findAll() {
        String query = String.format(Executor.FIND_ALL, TABLE_NAME);
        return new Executor(dataSource.getConnection()).execQuery(query, rs -> {
            List<Phone> phones = new ArrayList<>();
            while (rs.next()){
                Phone phone = new Phone();
                phone.setKey(rs.getLong(KEY_COL));
                phone.setModel(rs.getString(MODEL_COL));
                phone.setPrice(rs.getBigDecimal(PRICE_COL));
                phones.add(phone);
            }
            return phones;
        });
    }

    @Override
    public void close() {
        dataSource.closeQuietly();
    }

    private static class JdbcPhoneDaoHolder{
        private static final JdbcPhoneDao INSTANCE = new JdbcPhoneDao();
        private JdbcPhoneDaoHolder() {}
    }

}
