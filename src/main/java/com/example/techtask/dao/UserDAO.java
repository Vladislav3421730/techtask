package com.example.techtask.dao;

import com.example.techtask.model.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDAO {

    private final SessionFactory sessionFactory;

    private static final String FIND_USERS_HQL= """
             SELECT u FROM User u JOIN u.orders o
             WHERE o.orderStatus = 'PAID'
             AND EXTRACT(YEAR FROM o.createdAt) = 2010 """;


    private static final String FIND_USER_HQL="""
            SELECT u FROM User u JOIN u.orders o
            WHERE o.orderStatus = 'DELIVERED'
            AND EXTRACT(YEAR FROM o.createdAt) = 2003
            GROUP BY u.id
            ORDER BY SUM(o.price * o.quantity) DESC LIMIT 1""";

    public Optional<User> findUserWithMaxSumOfDeliveredOrdersIn2003(){
        Session session = sessionFactory.getCurrentSession();
        List<User> users=session.createQuery(FIND_USER_HQL, User.class).setMaxResults(1).list();
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    public List<User> findUsersWithPaidOrdersIn2010() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FIND_USERS_HQL, User.class).list();
    }


}
