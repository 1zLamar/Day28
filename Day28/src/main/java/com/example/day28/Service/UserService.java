package com.example.day28.Service;

import com.example.day28.ApiException.ApiException;
import com.example.day28.Model.Order;
import com.example.day28.Model.Product;
import com.example.day28.Model.User;
import com.example.day28.Repository.OrderRepository;
import com.example.day28.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public void register(User user){
        String hash=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setRole("CUSTOMER");
        userRepository.save(user);
    }

    public void update(Integer userId, User user) {
        User user1 = userRepository.findUserById(userId);
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user1.setPassword(hash);
        userRepository.save(user1);
    }

    public void delete(Integer userId) {
        User user1 = userRepository.findUserById(userId);
        userRepository.delete(user1);
    }

    public User getUserById(Integer id){
        User user=userRepository.findUserById(id);
        if(user==null){
            throw new ApiException("not found");
        }
        return user;
    }

}
