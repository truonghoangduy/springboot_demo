package com.example.demo.demo.services.Product;

import com.example.demo.demo.entities.Category;
import com.example.demo.demo.entities.Product;
import java.util.List;
import java.util.Optional;

public interface IProductService {
  List<Product> findAll();
  Optional<Product> findById(Long id);
  Product save(Product product);
  void delete(Long id);
}
