package com.example.techtask.service.impl;

import com.example.techtask.dao.OrderDAO;
import com.example.techtask.model.Order;
import com.example.techtask.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    @Override
    public Order findOrder() {
        return orderDAO.findNewOrderWithQuantityMoreThanOne().orElse(null);
    }

    @Override
    public List<Order> findOrders() {
        return orderDAO.findOrdersWithActiveUserOrderByCreating();
    }
}
