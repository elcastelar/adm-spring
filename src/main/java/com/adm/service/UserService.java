package com.adm.service;

import com.adm.dao.UserDao;
import com.adm.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends GenericService<User, UserDao> {

    public UserService(UserDao genericDao) {
        super(genericDao);
    }

    @Transactional
    public User checkUser(User user) {
        return genericDao.checkUser(user);
    }
}
