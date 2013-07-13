package net.lampetty.samples.spring;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AbstractParentSample {

    public static void main(String[] args) throws Exception {
        new AbstractParentSample().run(args);
    }
    
    public void run(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("database.xml");
        BasicDataSource dataSource = context.getBean(BasicDataSource.class);
        System.out.println("url = " + dataSource.getUrl());
        //Connection c = dataSource.getConnection();
    }
    
}
