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
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    User user1,user2,user3;

    @BeforeEach
    void setUp() {
        user1= new User(null, "Sara", "12345", "CUSTOMER", null);
        user2 = new User(null, "Jumanah", "jumanah123", "CUSTOMER", null);
        user3 = new User(null, "Yara", "YYY", "CUSTOMER", null);
    }

    @Test
    public void findUserByUsername() {
        userRepository.save(user1);
        User user = userRepository.findUserByUsername(user1.getUsername());
        Assertions.assertThat(user).isEqualTo(user1);
    }

    @Test
    public void findUserById() {
        userRepository.save(user2);
        User user = userRepository.findUserById(user2.getId());
        Assertions.assertThat(user).isEqualTo(user2);
    }
}