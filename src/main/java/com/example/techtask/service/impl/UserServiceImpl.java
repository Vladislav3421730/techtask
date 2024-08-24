package com.example.techtask.service.impl;

import com.example.techtask.dao.UserDAO;
import com.example.techtask.model.User;
import com.example.techtask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Override
    public User findUser() {
        return userDAO.findUserWithMaxSumOfDeliveredOrdersIn2003().orElse(null);
    }

    @Override
    public List<User> findUsers() {
        return userDAO.findUsersWithPaidOrdersIn2010();
    }
}
