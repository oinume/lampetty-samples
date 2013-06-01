package net.lampetty.samples.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private DataSource dataSource;
    
    @Transactional
    public void insert() throws Exception {
        Connection c = dataSource.getConnection();
        PreparedStatement s = c.prepareStatement("insert into user (name) values ('oinume')");
        s.execute();
        s.close();
        PreparedStatement s2 =  c.prepareStatement("insert into user (name) values ('oinume2')");
        s2.execute();
        s2.close();
        c.close();
    }

}
