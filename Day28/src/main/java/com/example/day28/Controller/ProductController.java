package com.example.day28.Controller;

import com.example.day28.ApiException.ApiException;
import com.example.day28.Model.Order;
import com.example.day28.Model.Product;
import com.example.day28.Model.User;
import com.example.day28.Service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getProduct(){
        List<Product> products=productService.getProduct();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping ("/add")
    public ResponseEntity addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return ResponseEntity.status(200).body("Product Added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody Product product,@PathVariable Integer id) {
        productService.update(product,id);
        return ResponseEntity.status(200).body("Product updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(200).body("Product deleted");
    }
    @GetMapping("/get-id/{id}")
    public ResponseEntity getProductById(@PathVariable Integer id){
        Product product=productService.getProductById(id);
        return ResponseEntity.status(200).body(product);
    }
}
