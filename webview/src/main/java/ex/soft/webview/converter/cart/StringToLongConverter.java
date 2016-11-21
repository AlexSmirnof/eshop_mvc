package ex.soft.webview.converter.cart;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Alex108 on 21.11.2016.
 */
@Component("quantityConverter")
public class StringToLongConverter implements Converter<String, Long> {

    @Override
    public Long convert(String s) {
        System.out.println("CONVERT :: " + s );
        return Long.valueOf(s);
    }
}
