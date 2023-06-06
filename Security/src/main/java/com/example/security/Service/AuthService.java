package com.example.security.Service;

import com.example.security.Model.MyUser;
import com.example.security.Repository.AuthRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRespository authRespository;

    public List<MyUser> getUser(){
        return authRespository.findAll();
    }

    public void register(MyUser myUser){
        String hash=new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hash);
        myUser.setRole("USER");
        authRespository.save(myUser);
    }

}
