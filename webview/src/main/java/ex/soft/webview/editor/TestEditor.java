package ex.soft.webview.editor;

import java.beans.PropertyEditorSupport;

/**
 * Created by Alex108 on 21.11.2016.
 */
public class TestEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        System.out.println("getAsText :: " );
        return super.getAsText();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        System.out.println("setAsText :: " + text);
        super.setAsText(text);
    }
}
