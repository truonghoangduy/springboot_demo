package com.example.demo.demo.controller;

import com.example.demo.demo.entities.Category;
import com.example.demo.demo.entities.Product;
import com.example.demo.demo.response.ResponseMessange;
import com.example.demo.demo.services.Category.CategoryServiceImpl;
import com.example.demo.demo.services.Product.ProductServiceImpl;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {
  @Autowired
  ProductServiceImpl productService;

  @Autowired
  CategoryServiceImpl categoryService;

  @PostMapping
  public ResponseEntity<?> createProduct(@RequestBody Product product){
    //Product product1 = new Product();
    if(product == null){
      return new ResponseEntity<>(new ResponseMessange("maybe you are missing some data!"), HttpStatus.OK);
    }
//    if(product.getName().trim().isEmpty() || product.getCategory() == null ||
//    product.getDescription().trim().isEmpty() || product.getPrice() == null ||
//    product.getQuantity().intValue()==0 || product.getImageURL().trim().isEmpty() ||
//    product.getPromotion_price() == null){
//      return new ResponseEntity<>(new ResponseMessange("Name is required!"), HttpStatus.OK);
//    }
//    product = product1;
    productService.save(product);
    return new ResponseEntity<>(new ResponseMessange("Create product success!"), HttpStatus.OK);
  }
//  @PostMapping
//  public ResponseEntity<?> createProduct(@RequestBody Product product){
//    try {
//      Product result = productService.save(product);
//      return new ResponseEntity<>(result, HttpStatus.OK);
//    } catch (IllegalArgumentException ex) {
//      return new ResponseEntity(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
//    }
//  }

  @GetMapping
  public ResponseEntity<?> getListProduct(){
    List<Product> products = productService.findAll();
    if(products.isEmpty()){
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(products , HttpStatus.OK);
  }
  @GetMapping("/{id}")
  public ResponseEntity<?> getDetailProduct(@PathVariable Long id){
    Optional<Product> product = productService.findById(id);
    if(!product.isPresent()){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(product, HttpStatus.OK);
  }
//  @PutMapping("/{id}")
//  public ResponseEntity<?> updateCategory(@RequestBody Category category,@PathVariable Long id){
//    Optional<Product> product = productService.findById(id);
//    if(!product.isPresent()){
//      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//    if(product.getName().trim().isEmpty() || category.getDescription().trim().isEmpty()){
//      return new ResponseEntity<>(new ResponseMessange("Name or Description is required!"),HttpStatus.OK);
//    }
//    category1.get().setName(category.getName());
//    category1.get().setDescription(category.getDescription());
//    categoryService.save(category1.get());
//    return new ResponseEntity<>(new ResponseMessange("Update success!"), HttpStatus.OK);
//  }
//
//  @DeleteMapping("/{id}")
//  public ResponseEntity<?> deleteCategoryById(@PathVariable Long id){
//    Optional<Category> category = categoryService.findById(id);
//    if(!category.isPresent()){
//      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//    categoryService.delete(category.get().getId());
//    return new ResponseEntity<>( new ResponseMessange("Delete success!"), HttpStatus.OK);
//  }
}
