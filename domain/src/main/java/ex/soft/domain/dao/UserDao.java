package ex.soft.domain.dao;

import ex.soft.domain.model.User;

/**
 * Created by Alex108 on 25.10.2016.
 */
public interface UserDao {

    User get(Long key);

    Long save(User user);

    void delete(Long key);

}
