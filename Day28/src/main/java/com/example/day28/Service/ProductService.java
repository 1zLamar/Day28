package com.example.day28.Service;

import com.example.day28.ApiException.ApiException;
import com.example.day28.Model.Order;
import com.example.day28.Model.Product;
import com.example.day28.Model.User;
import com.example.day28.Repository.OrderRepository;
import com.example.day28.Repository.ProductRepository;
import com.example.day28.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public List<Product> getProduct(){
        return  productRepository.findAll();
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public void update(Product product,Integer productId) {
        Product product1 = productRepository.findProductById(productId);
        if(product1==null){
            throw new ApiException("not found");
        }
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        productRepository.save(product1);
    }

    public void deleteProduct(Integer productId) {
        Product product1=productRepository.findProductById(productId);
        if(product1==null){
            throw new ApiException("not found");
        }
        productRepository.delete(product1);
    }
    public Product getProductById(Integer id){
        Product product=productRepository.findProductById(id);
        if(product==null){
            throw new ApiException("not found");
        }
        return product;
    }
}
