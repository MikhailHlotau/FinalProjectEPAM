package glotov.servlet.service.impl;

import glotov.servlet.service.CustomerService;

public class UserServiceImpl implements CustomerService {

    private static final UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String userName, String password){
        return userName.equals(password);
    }
}
