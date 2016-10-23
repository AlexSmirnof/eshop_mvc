package ex.soft.service;

import ex.soft.domain.dao.IDao;
import ex.soft.domain.model.Client;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Alex108 on 23.10.2016.
 */
public class ClientService {

    @Autowired
    private IDao<Client> clientDao;

    public Client getClient(Long key){
        return clientDao.get(key);
    }

    public void saveClient(Client client){
        clientDao.save(client);
    }

    public void deleteClient(Long key){

    }
}
