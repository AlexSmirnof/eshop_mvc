package ex.soft.service;

import java.util.List;

/**
 * Created by Alex108 on 12.10.2016.
 */
public interface IService<E> {

    E get(Long key);

    void save(E e);

    List<E> findAll();

    void close();

}
