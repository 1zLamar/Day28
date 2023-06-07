package com.example.day28;

import com.example.day28.Model.Order;
import com.example.day28.Model.Product;
import com.example.day28.Model.User;
import com.example.day28.Repository.OrderRepository;
import com.example.day28.Repository.ProductRepository;
import com.example.day28.Repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    Order order1,order2,order3;
    User user;
    Product product;

    List<Order> orderList;

    LocalDate date1=LocalDate.of(2023,3,2);
    LocalDate date2=LocalDate.of(2023,6,1);
    LocalDate date3=LocalDate.of(2023,6,5);

    @BeforeEach
    void setUp() {
        user= new User(null, "Sara", "12345", "CUSTOMER", null);

        order1=new Order(null,20,39,date1,"completed",user,null);
        order2=new Order(null,39,67,date2,"inProgress",user,null);
        order3=new Order(null,48,94,date3,"new",user,null);

        product=new Product(null,"SheaMoisture Leave-In",54, Set.of(order1,order2,order3));

    }
    @Test
    public void findOrderById(){
        userRepository.save(user);
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        Order order = orderRepository.findOrderById(order1.getId());
        Assertions.assertThat(order).isEqualTo(order1);
    }

    @Test
    public void findOrderByUser() {
        userRepository.save(user);
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderList=orderRepository.findOrderByUser(user);
        Assertions.assertThat(orderList.get(0).getUser().getId()).isEqualTo(user.getId());
    }
}
