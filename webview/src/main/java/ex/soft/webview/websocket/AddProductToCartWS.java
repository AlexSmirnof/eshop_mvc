package ex.soft.webview.websocket;

import ex.soft.service.CartService;

import javax.annotation.Resource;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Alex108 on 22.10.2016.
 */
@ServerEndpoint("/addToCartWS")
public class AddProductToCartWS {

    @Resource("cartService")
    private CartService cartService;

    @OnMessage
    public String addProductToCart(Session session, String msg){
//        PARSE MESSAGE
        cartService.addToCart( 1l, 1l, 1);
//        RETURN NEW QUANTITY AND PRICE FOR CART
        return "";
    }
}
