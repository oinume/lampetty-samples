package net.lampetty.samples.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import net.lampetty.samples.spring.UserService;

public class TransactionalSample {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        new TransactionalSample().run(args);
    }

    // create table user (id integer not null auto_increment, name varchar(255), primary key (id));
    public void run(String[] args) throws Exception {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("database.xml");
        UserService userService = context.getBean(UserService.class);
        userService.insert();
    }
}
