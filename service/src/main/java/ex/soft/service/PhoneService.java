package ex.soft.service;

import ex.soft.domain.dao.PhoneDao;
import ex.soft.domain.model.Phone;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Alex108 on 14.10.2016.
 */
public class PhoneService implements IService<Phone> {

    private PhoneDao phoneDao;

    public PhoneService() {}

    public void setPhoneDao(PhoneDao phoneDao) {
        this.phoneDao = phoneDao;
    }

    @Transactional
    @Override
    public Phone get(Long key) {
        return null;
    }

    @Transactional
    @Override
    public void save(Phone phone) {

    }

    @Transactional
    @Override
    public List<Phone> findAll() {
        return null;
    }

    @Transactional
    @Override
    public void close() {

    }
}
