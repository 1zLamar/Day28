package com.example.day28;

import com.example.day28.Model.Order;
import com.example.day28.Model.Product;
import com.example.day28.Model.User;
import com.example.day28.Repository.OrderRepository;
import com.example.day28.Repository.ProductRepository;
import com.example.day28.Repository.UserRepository;
import com.example.day28.Service.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderServiceTest {

    @InjectMocks
    OrderService orderService;
    @Mock
    OrderRepository orderRepository;
    @Mock
    ProductRepository productRepository;

    @Mock
    UserRepository userRepository;

    Order order1,order2;
    Product product;
    User user;

    Order order;

    List<Order> orderList;

    LocalDate date1=LocalDate.of(2023,3,2);
    LocalDate date2=LocalDate.of(2023,6,1);
    LocalDate date3=LocalDate.of(2023,6,5);

    @BeforeEach
    void setUp(){

        user = new User(null , "Tala", "12345", "Customer", null);

        order1 = new Order(null, 1, 52, date1, "new", user, product);
        order2 = new Order(null, 2, 239, date2, "new", user, product);

        product = new Product(null, "Book", 28, Set.of(order1,order2));


        orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);

    }

    @Test
    public void getOrderbyId(){
        when(orderRepository.findOrderById(order1.getId())).thenReturn(order1);
        Order orderA = orderService.getOrderById(user.getId(), order1.getId());
        Assertions.assertThat(orderA).isEqualTo(order1);
        verify(orderRepository,times(1)).findOrderById(order1.getId());
    }

    @Test
    public void deleteOrder(){
        when(orderRepository.findOrderById(order1.getId())).thenReturn(order1);
        orderService.deleteOrder(user.getId(), order1.getId());
        verify(orderRepository,times(1)).findOrderById(order1.getId());
        verify(orderRepository,times(1)).delete(order1);
    }


}
