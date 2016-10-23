package ex.soft.webview.listener;

import ex.soft.domain.model.Client;
import ex.soft.service.CartService;
import ex.soft.service.ClientService;

import javax.annotation.Resource;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Alex108 on 23.10.2016.
 */
@WebListener
public class SessionCreationListener implements HttpSessionListener{

    @Resource
    private ClientService clientService;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        Long key = httpSessionEvent.getSession().getCreationTime();
        Client client = new Client();
        client.setKey(key);
        clientService.saveClient(client);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        Long key = httpSessionEvent.getSession().getCreationTime();
        clientService.deleteClient(key);
    }
}
