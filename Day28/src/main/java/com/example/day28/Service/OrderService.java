package com.example.day28.Service;

import com.example.day28.ApiException.ApiException;
import com.example.day28.DTO.OrderDto;
import com.example.day28.Model.Order;
import com.example.day28.Model.Product;
import com.example.day28.Model.User;
import com.example.day28.Repository.OrderRepository;
import com.example.day28.Repository.ProductRepository;
import com.example.day28.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public void addOrder(Integer userId, OrderDto dto){
        Product product = productRepository.findProductById(dto.getProductId());
        User user = userRepository.findUserById(userId);
        int totalPrice = product.getPrice() * dto.getQuantity();
        Order order = new Order(null, dto.getQuantity(), totalPrice, new Date(), "new", user, product);
        orderRepository.save(order);
    }

    public void updateOrder(Integer userId, Order order, Integer orderId) {
        Order order1=orderRepository.findOrderById(orderId);
        if(order1==null){
            throw new ApiException("not found");
        }
        if(userId!=order1.getUser().getId()){
            throw new ApiException("unauthorized");
        }
        order1.setQuantity(order.getQuantity());
        order1.setStatus(order.getStatus());
        order1.setDateReceived(order.getDateReceived());
        order1.setTotalPrice(order1.getTotalPrice());
        orderRepository.save(order1);
    }

    public void deleteOrder(Integer userId, Integer orderId) {
        Order order=orderRepository.findOrderById(orderId);
        if(order==null){
            throw new ApiException("not found");
        }
        if(userId!=order.getUser().getId()){
            throw new ApiException("unauthorized");
        }
        if (order.getStatus().equals("inProgress")|order.getStatus().equals("completed")){
            throw new ApiException("you can't delete order");
        }
        order.setUser(null);
        orderRepository.delete(order);
    }

    public Order getOrderById(Integer userId,Integer orderId){
        Order order=orderRepository.findOrderById(orderId);
        if(userId!=order.getUser().getId()){
            throw new ApiException("not found");
        }
        return order;
    }

    public void changeStatus(Integer id){
        Order order=orderRepository.findOrderById(id);
        if(order==null){
            throw new ApiException("not found");
        }
        if(order.getStatus()=="new"){
            order.setStatus("inProgress");
        }
        if(order.getStatus()=="inProgress"){
            order.setStatus("completed");
        }
        orderRepository.save(order);
    }

    public List<Order> getUserOrders(Integer userId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ApiException("user not found with ID ");
        }
        return orderRepository.findOrderByUser(user);
    }
}
