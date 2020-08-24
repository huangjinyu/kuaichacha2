package com.kcc.service.impl;

import com.kcc.service.OrderService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    public OrderServiceImpl() {
        System.out.println("OrderServiceImpl");
    }
}