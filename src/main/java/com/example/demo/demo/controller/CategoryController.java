package com.example.demo.demo.controller;

import com.example.demo.demo.entities.Category;
import com.example.demo.demo.response.ResponseMessange;
import com.example.demo.demo.services.Category.CategoryServiceImpl;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController {

  @Autowired
  CategoryServiceImpl categoryService;

  @PostMapping
  public ResponseEntity<?> createCategory(@RequestBody Category category){
    if(category.getName().trim().isEmpty() || category.getDescription().trim().isEmpty()){
      return new ResponseEntity<>(new ResponseMessange("Name or Description is required!"), HttpStatus.OK);
    }
    categoryService.save(category);
    return new ResponseEntity<>(new ResponseMessange("Create success!"), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<?> getListCategory(){
    List<Category> categories = categoryService.findAll();
    if(categories.isEmpty()){
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(categories, HttpStatus.OK);
  }
  @GetMapping("/{id}")
  public ResponseEntity<?> getDetailCategory(@PathVariable Long id){
    Optional<Category> category = categoryService.findById(id);
    if(!category.isPresent()){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(category, HttpStatus.OK);
  }
  @PutMapping("/{id}")
  public ResponseEntity<?> updateCategory(@RequestBody Category category,@PathVariable Long id){
    Optional<Category> category1 = categoryService.findById(id);
    if(!category1.isPresent()){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    if(category.getName().trim().isEmpty() || category.getDescription().trim().isEmpty()){
      return new ResponseEntity<>(new ResponseMessange("Name or Description is required!"),HttpStatus.OK);
    }
    category1.get().setName(category.getName());
    category1.get().setDescription(category.getDescription());
    categoryService.save(category1.get());
    return new ResponseEntity<>(new ResponseMessange("Update success!"), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteCategoryById(@PathVariable Long id){
    Optional<Category> category = categoryService.findById(id);
    if(!category.isPresent()){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    categoryService.delete(category.get().getId());
    return new ResponseEntity<>( new ResponseMessange("Delete success!"), HttpStatus.OK);
  }

}
