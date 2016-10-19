package ex.soft.domain.dao.jdbc;

import ex.soft.domain.dao.IDao;
import ex.soft.domain.model.Client;

import java.util.List;

/**
 * Created by Alex108 on 19.10.2016.
 */
public class JdbcClientDao implements IDao<Client> {



    @Override
    public Client get(Long key) {
        return null;
    }

    @Override
    public void save(Client client) {

    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public void close() {

    }
}
