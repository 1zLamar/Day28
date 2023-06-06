package com.example.day28.Repository;

import com.example.day28.Model.Order;
import com.example.day28.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findOrderByUser(User user);

    Order findOrderById(Integer orderId);



}
