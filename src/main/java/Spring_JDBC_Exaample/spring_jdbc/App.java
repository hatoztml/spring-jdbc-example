package Spring_JDBC_Exaample.spring_jdbc;

import java.util.Date;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.proje.model.User;
import com.proje.service.UserService;
import com.proje.service.impl.UserServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

    	ConfigurableApplicationContext configurableApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    	
    	UserService userService = configurableApplicationContext.getBean("userServiceImpl",UserServiceImpl.class);
    	
    	User user = new User(1, "hatoztml", "hato", new Date());
    	
    	userService.save(user);
    	
    	configurableApplicationContext.close();
    	
    }
}
