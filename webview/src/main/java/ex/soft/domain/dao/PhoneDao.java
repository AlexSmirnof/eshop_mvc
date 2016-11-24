package ex.soft.domain.dao;

import ex.soft.domain.model.Phone;

import java.util.List;

/**
 * Created by Alex108 on 11.10.2016.
 */
public interface PhoneDao {

    Phone get(Long key);

    void save(Phone phone);

    List<Phone> findAll();

    void close();
}
