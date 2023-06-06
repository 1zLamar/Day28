package com.example.day28.Repository;

import com.example.day28.Model.Order;
import com.example.day28.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    Product findProductById(Integer id);

    List<Product> findProductByOrder(Order order);
}
