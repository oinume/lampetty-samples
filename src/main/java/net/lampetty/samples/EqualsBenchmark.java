package net.lampetty.samples;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * equalsメソッドを下記方法によって実装してベンチマークをとる。
 * 
 * <ul>
 * <li>EqualsBuilderを使わない普通の実装</li>
 * <li>EqualsBuilderを使った実装</li>
 * <li>EqualsBuilder.reflectionEqualsを使った実装</li>
 * </ul>
 */
public class EqualsBenchmark {

    private static final Logger LOG = LoggerFactory.getLogger(EqualsBenchmark.class);
    
    public static void main(String[] args) {
        new EqualsBenchmark().run(args);
    }
    
    private void run(String[] args) {
        String arg = args.length == 0 ? "100000" : args[0];
        int num = NumberUtils.toInt(arg);
        long normalElapsed = normal(num);
        long builderElapsed = builder(num);
        long reflectionElapsed = reflection(num);
        System.out.printf("normal     = %04d ms%n", normalElapsed);
        System.out.printf("builder    = %04d ms%n", builderElapsed);
        System.out.printf("relection  = %04d ms%n", reflectionElapsed);
    }
    
    private long normal(int num) {
        List<NormalUser> users1 = new ArrayList<NormalUser>();
        List<NormalUser> users2 = new ArrayList<NormalUser>();
        
        for (int i = 0; i < num; i++) {
            String name = "normal_" + i;
            NormalUser user1 = new NormalUser(i, name, name + "@gmail.com", new Date(), new Date());
            NormalUser user2 = new NormalUser(i, name, name + "@gmail.com", new Date(), new Date());
            users1.add(user1);
            users2.add(user2);
        }
        
        long startedAt = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            NormalUser user1 = users1.get(i);
            NormalUser user2 = users2.get(i);
            boolean result = user1.equals(user2);
            LOG.trace("result = {}", result);
        }
        long elapsed = System.currentTimeMillis() - startedAt;
        return elapsed;
    }
    
    private long builder(int num) {
        List<BuilderUser> users1 = new ArrayList<BuilderUser>();
        List<BuilderUser> users2 = new ArrayList<BuilderUser>();
        
        for (int i = 0; i < num; i++) {
            String name = "normal_" + i;
            BuilderUser user1 = new BuilderUser(i, name, name + "@gmail.com", new Date(), new Date());
            BuilderUser user2 = new BuilderUser(i, name, name + "@gmail.com", new Date(), new Date());
            users1.add(user1);
            users2.add(user2);
        }
        
        long startedAt = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            BuilderUser user1 = users1.get(i);
            BuilderUser user2 = users2.get(i);
            boolean result = user1.equals(user2);
            LOG.debug("result = {}", result);
        }
        long elapsed = System.currentTimeMillis() - startedAt;
        return elapsed;
    }

    private long reflection(int num) {
        List<ReflectionUser> users1 = new ArrayList<ReflectionUser>();
        List<ReflectionUser> users2 = new ArrayList<ReflectionUser>();
        
        for (int i = 0; i < num; i++) {
            String name = "normal_" + i;
            ReflectionUser user1 = new ReflectionUser(i, name, name + "@gmail.com", new Date(), new Date());
            ReflectionUser user2 = new ReflectionUser(i, name, name + "@gmail.com", new Date(), new Date());
            users1.add(user1);
            users2.add(user2);
        }
        
        long startedAt = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            ReflectionUser user1 = users1.get(i);
            ReflectionUser user2 = users2.get(i);
            boolean result = user1.equals(user2);
            LOG.trace("result = {}", result);
        }
        long elapsed = System.currentTimeMillis() - startedAt;
        return elapsed;
    }

    // ベースクラス
    private static abstract class User {
        int id;
        String name;
        String email;
        Date birthday;
        Date lastLoginedAt;
        
        User(int id, String name, String email,
                Date birthday, Date lastLoginedAt) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.birthday = birthday;
            this.lastLoginedAt = lastLoginedAt;
        }
    }
    
    // 普通に実装
    private static class NormalUser extends User {
        NormalUser(int id, String name, String email,
                Date birthday, Date lastLoginedAt) {
            super(id, name, email, birthday, lastLoginedAt);
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof User)) {
                return false;
            }
            
            User other = (User)o;
            return this.id == other.id
                    && this.name.equals(other.name)
                    && this.email.equals(other.email)
                    && this.birthday.getTime() == other.birthday.getTime()
                    && this.lastLoginedAt.getTime() == other.lastLoginedAt.getTime();
        }
    }
    
    // EqualsBuilderなどを使う
    private static class BuilderUser extends User {
        BuilderUser(int id, String name, String email,
                Date birthday, Date lastLoginedAt) {
            super(id, name, email, birthday, lastLoginedAt);
        }
        
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof User)) {
                return false;
            }
            User other = (User)o;
            return new EqualsBuilder()
                .append(this.id, other.id)
                .append(this.name, other.name)
                .append(this.email, other.email)
                .append(this.birthday, other.birthday)
                .append(this.lastLoginedAt, other.lastLoginedAt)
                .build();
        }
    }
    
    // EqualsBuilder.reflectionEqualsを使う
    private static class ReflectionUser extends User {
        ReflectionUser(int id, String name, String email,
                Date birthday, Date lastLoginedAt) {
            super(id, name, email, birthday, lastLoginedAt);
        }

        @Override
        public boolean equals(Object o) {
            return EqualsBuilder.reflectionEquals(this, o, false);
        }
    }
}
