package com.example.day28.Controller;

import com.example.day28.DTO.OrderDto;
import com.example.day28.Model.Order;
import com.example.day28.Model.Product;
import com.example.day28.Model.User;
import com.example.day28.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/get")
    public ResponseEntity getOrders(@AuthenticationPrincipal User user){
        List<Order> orders=orderService.getUserOrders(user.getId());
        return ResponseEntity.status(200).body(orders);
    }

    @PostMapping("/add")
    public ResponseEntity addOrder(@AuthenticationPrincipal User user, @RequestBody OrderDto dto){
        orderService.addOrder(user.getId(), dto);
        return ResponseEntity.status(200).body("order added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@AuthenticationPrincipal User user, @RequestBody Order order, @PathVariable Integer id){
        orderService.updateOrder(user.getId(),order,id);
        return ResponseEntity.status(200).body("order updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@AuthenticationPrincipal User user,@PathVariable Integer id){
        orderService.deleteOrder(user.getId(),id);
        return ResponseEntity.status(200).body("order deleted");
    }
    @GetMapping("/get-id/{id}")
    public ResponseEntity getOrderById(@AuthenticationPrincipal User user,@PathVariable Integer id){
        Order order=orderService.getOrderById(user.getId(), id);
        return ResponseEntity.status(200).body(order);
    }
    @PutMapping("/change-status/{id}")
    public ResponseEntity changeStatus(@PathVariable Integer id){
        orderService.changeStatus(id);
        return ResponseEntity.status(200).body("status changed");
    }


}
