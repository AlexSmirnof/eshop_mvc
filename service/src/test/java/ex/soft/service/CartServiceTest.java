package ex.soft.service;

import ex.soft.domain.model.Cart;
import ex.soft.domain.model.Phone;
import ex.soft.service.api.ICartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by Alex108 on 04.11.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    private ICartService cartService;

    public Phone phone = new Phone();

    @Mock
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        cartService = new CartService();
        when(session.getAttribute(anyString())).thenReturn(new Cart());
        doNothing().when(session).setAttribute(anyString(), any(Cart.class));
    }

    @Test
    public void getCart() throws Exception {
        Cart cart = cartService.getCart(session);
        assertNotNull(cart);
    }

    @Test
    public void addProductToCart() throws Exception {
//        cartService.addToCart(session, phone, 1L);
//        verify(session,times(1)).getAttribute(anyString());
//        verify(session,times(1)).setAttribute(anyString(), any(Cart.class));
    }

    @Test(expected = RuntimeException.class)
    public void deleteProductFromCart() throws Exception {
//        cartService.deleteFromCart(session, phone, 1L);
    }
}