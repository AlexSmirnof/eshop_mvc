package ex.soft.service;

import ex.soft.domain.dao.UserDao;
import ex.soft.domain.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Alex108 on 23.10.2016.
 */
@Service("userService")
public class UserService {

    private UserDao userDao;

    public User getClient(Long key){
        return userDao.get(key);
    }

    public Long saveUser(User user){
        return userDao.save(user);
    }

    @Resource( name = "userDao")
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
