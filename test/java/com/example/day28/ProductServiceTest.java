package com.example.day28;

import com.example.day28.Model.Order;
import com.example.day28.Model.Product;
import com.example.day28.Model.User;
import com.example.day28.Repository.OrderRepository;
import com.example.day28.Repository.ProductRepository;
import com.example.day28.Repository.UserRepository;
import com.example.day28.Service.OrderService;
import com.example.day28.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;
    @Mock
    ProductRepository productRepository;
    @Mock
    OrderRepository orderRepository;

    @Mock
    UserRepository userRepository;

    Order order1,order2;
    Product product1,product2,product3;
    User user;

    Order order;

    List<Order> orderList;
    List<Product> products;


    LocalDate date1=LocalDate.of(2023,3,2);
    LocalDate date2=LocalDate.of(2023,6,1);

    @BeforeEach
    void setUp(){

        user = new User(null , "Tala", "12345", "Customer", null);

        order1 = new Order(null, 1, 52, date1, "new", user, null);
        order2 = new Order(null, 2, 239, date2, "new", user, null);

        product1=new Product(null,"SheaMoisture Leave-In",54, Set.of(order1,order2));
        product2=new Product(null,"SheaMoisture Jamaican Black Castor Oil Shampoo",54, Set.of(order1,order2));
        product3=new Product(null,"SheaMoisture Jamaican Black Castor Oil Conditioner",54, Set.of(order1));

        orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);
    }

    @Test
    public void addProduct(){
        productService.addProduct(product1);
        verify(productRepository,times(1)).save(product1);
    }

    @Test
    public void update(){
        when(productRepository.findProductById(product2.getId())).thenReturn(product2);
        productService.update(product3,product2.getId());
        verify(productRepository,times(1)).findProductById(product2.getId());
        verify(productRepository,times(1)).save(product2);
    }
    @Test
    public void delete(){
        when(productRepository.findProductById(product2.getId())).thenReturn(product2);
        productService.deleteProduct(product2.getId());
        verify(productRepository,times(1)).findProductById(product2.getId());

    }
}
