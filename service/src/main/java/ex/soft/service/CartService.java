package ex.soft.service;

import ex.soft.domain.dao.IDao;
import ex.soft.domain.model.Cart;
import ex.soft.domain.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Alex108 on 19.10.2016.
 */
@Service//("cartService")
public class CartService {

    @Autowired
    private IDao<Client> clientDao;

    @Transactional
    public Cart getCart(Long clientId){
        return clientDao.get(clientId).getCart();
    }

    @Transactional
    public void addToCart(Long clientId, Long productId, Integer quantity){
        clientDao.get(clientId).getCart().getProductsAndQuantities().merge(productId, quantity, Integer::sum);
    }

}
