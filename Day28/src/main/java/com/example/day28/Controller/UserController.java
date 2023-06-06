package com.example.day28.Controller;

import com.example.day28.Model.User;
import com.example.day28.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody User user){
        userService.register(user);
        return ResponseEntity.status(200).body(" user registered ");
    }

    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body("Login");
    }

    @PostMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("Logout");
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@AuthenticationPrincipal User user,@RequestBody User user1) {
        userService.update(user.getId(), user1);
        return ResponseEntity.status(200).body("user updated");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@AuthenticationPrincipal User user) {
        userService.delete(user.getId());
        return ResponseEntity.status(200).body("user deleted");
    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return ResponseEntity.status(200).body(user);
    }

}
