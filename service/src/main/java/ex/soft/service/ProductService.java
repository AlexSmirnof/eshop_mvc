package ex.soft.service;

import ex.soft.domain.dao.ProductDao;
import ex.soft.domain.model.Phone;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Alex108 on 19.10.2016.
 */
@Service
public class ProductService {

    private ProductDao<Phone> productDao;

    @Resource(name = "phoneDao")
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Transactional
    public Phone getProduct(Long key) {
        return productDao.get(key);
    }

    @Transactional
    public List<Phone> listProducts(){
        return productDao.findAll();
    }

}
