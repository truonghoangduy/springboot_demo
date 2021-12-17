package com.example.demo.demo.services.Category;

import com.example.demo.demo.entities.Category;
import java.util.List;
import java.util.Optional;

public interface ICategoryService {
  List<Category> findAll();
  Optional<Category> findById(Long id);
  Category save(Category category);
  void delete(Long id);
}
