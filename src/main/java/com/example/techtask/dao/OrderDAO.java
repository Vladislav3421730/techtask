package com.example.techtask.dao;


import com.example.techtask.model.Order;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderDAO {

    private final SessionFactory sessionFactory;

    private final static String FIND_ORDER_HQL= """
            SELECT o FROM Order o
            WHERE o.quantity>1
            ORDER BY o.createdAt DESC LIMIT 1
            """;

    private final static String FIND_ORDERS_HQL="""
           SELECT o FROM Order o 
           JOIN User u ON o.userId = u.id
           WHERE u.userStatus = 'ACTIVE'
           ORDER BY o.createdAt
           """;

    public Optional<Order> findNewOrderWithQuantityMoreThanOne() {
        Session session = sessionFactory.getCurrentSession();
        List<Order> results = session.createQuery(FIND_ORDER_HQL, Order.class).setMaxResults(1).list();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public List<Order> findOrdersWithActiveUserOrderByCreating(){
        Session session=sessionFactory.getCurrentSession();
        return session.createQuery(FIND_ORDERS_HQL, Order.class).list();
    }
}
