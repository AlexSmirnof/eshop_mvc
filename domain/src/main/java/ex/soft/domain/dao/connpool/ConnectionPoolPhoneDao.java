package ex.soft.domain.dao.connpool;

import ex.soft.domain.dao.PhoneDao;
import ex.soft.domain.model.Phone;

import java.util.List;

/**
 * Created by Alex108 on 12.10.2016.
 */
public class ConnectionPoolPhoneDao implements PhoneDao {
    @Override
    public Phone get(Long key) {
        return null;
    }

    @Override
    public void save(Phone phone) {

    }

    @Override
    public List<Phone> findAll() {
        return null;
    }

    @Override
    public void close() {

    }
}
