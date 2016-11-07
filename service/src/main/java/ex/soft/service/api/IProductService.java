package ex.soft.service.api;

import java.util.List;

/**
 * Created by Alex108 on 05.11.2016.
 */
public interface IProductService<P> {

    P getProduct(Long key);

    List<P> listProducts();

}
