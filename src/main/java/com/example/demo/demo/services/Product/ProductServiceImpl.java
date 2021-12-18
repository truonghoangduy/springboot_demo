package com.example.demo.demo.services.Product;

import com.example.demo.demo.entities.Category;
import com.example.demo.demo.entities.Product;
import com.example.demo.demo.repository.Category.ICategoryRepository;
import com.example.demo.demo.repository.Product.IProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService{

  @Autowired
  IProductRepository productRepository;

  @Autowired
  ICategoryRepository categoryRepository;

  @Override
  public List<Product> findAll() {
    return productRepository.findAll();
  }


  @Override
  public Optional<Product> findById(Long id) {
    return productRepository.findById(id);
  }

  @Override
  public Product save(Product product) {
    return productRepository.save(product);
  }

  @Override
  public void delete(Long id) {
    productRepository.deleteById(id);
  }
}
