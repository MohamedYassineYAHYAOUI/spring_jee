package fr.uge.jee.annotations.onlineshope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Application {


    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        var amazon = applicationContext.getBean( OnlineShop.class);
        //ApplicationContext context = new ClassPathXmlApplicationContext("config-online.xml");
        //OnlineShop amazon = context.getBean("Amazon", OnlineShop.class);
        amazon.printDescription();
    }
}
