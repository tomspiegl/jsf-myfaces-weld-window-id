package at.irian.myfaces.wscope.renderkit.html;

import org.apache.myfaces.renderkit.html.HtmlResponseStateManager;

import java.lang.reflect.Field;

public class WsHtmlResponseStateManager extends org.apache.myfaces.renderkit.html.HtmlResponseStateManager {

    public WsHtmlResponseStateManager() {

        try {
            Field field = HtmlResponseStateManager.class.getDeclaredField("_stateCacheFactory");
            field.setAccessible(true);
            field.set(this, new WsStateCacheFactoryImpl());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException("Error setting field _stateCacheFactory", e);
        }
    }
}
