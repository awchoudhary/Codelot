/**
 * Created by ramroop on 4/5/17.
 */
import com.codelot.Beans.User;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class OfyHelper implements  ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        ObjectifyService.register(User.class);

    }

    public void contextDestroyed(ServletContextEvent event) {
        //App Engine does not currently invoke this method
    }
}
