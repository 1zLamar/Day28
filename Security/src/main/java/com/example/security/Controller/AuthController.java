package com.example.security.Controller;

import com.example.security.Model.MyUser;
import com.example.security.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getAllUser(){
        List<MyUser> myuser=authService.getUser();
        return ResponseEntity.status(200).body(myuser);
    }

    @PostMapping("/reg")
    public ResponseEntity register(@RequestBody MyUser myUser){
        authService.register(myUser);
        return ResponseEntity.status(200).body("User register");
    }

    @PostMapping("/admin")
    public ResponseEntity admin(){
        return ResponseEntity.status(200).body("Welcome Admin");
    }

    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body("login");
    }

    @PostMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("logout");
    }

    @PostMapping("/user")
    public ResponseEntity user(){
        return ResponseEntity.status(200).body("Welcome User");
    }

}
