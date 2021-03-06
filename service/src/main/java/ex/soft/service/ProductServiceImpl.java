package ex.soft.service;

import ex.soft.domain.dao.PhoneDao;
import ex.soft.domain.model.Phone;
import ex.soft.service.api.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Alex108 on 19.10.2016.
 */
@Service
public class ProductServiceImpl implements ProductService<Phone> {

    @Resource(name = "phoneDao")
    private PhoneDao phoneDao;

    @Transactional
    public Phone getProduct(Long key) {
        return phoneDao.get(key);
    }

    @Transactional
    public List<Phone> listProducts(){
        return phoneDao.findAll();
    }


}
