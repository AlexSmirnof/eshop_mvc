package ex.soft.domain.dao;

import java.util.List;

/**
 * Created by Alex108 on 11.10.2016.
 */
public interface IDao<E> {

    E get(Long key);

    void save(E e);

    List<E> findAll();

    void close();

}
