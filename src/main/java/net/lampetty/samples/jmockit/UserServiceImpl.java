package net.lampetty.samples.jmockit;

import mockit.Injectable;

public class UserServiceImpl implements UserService {

    @Injectable
    private UserDao userDao;
    
    public User find(int id) {
        return userDao.findOne(id);
    }

}
