package com.example.demo.demo.controller;

import com.example.demo.demo.entities.Category;
import com.example.demo.demo.entities.Product;
import com.example.demo.demo.repository.Category.ICategoryRepository;
import com.example.demo.demo.response.ResponseMessange;
import com.example.demo.demo.services.Category.CategoryServiceImpl;
import com.example.demo.demo.services.Image.ImageService;
import com.example.demo.demo.services.Product.ProductServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("product")
public class ProductController {
  @Autowired
  ProductServiceImpl productService;

  @Autowired
  CategoryServiceImpl categoryService;

  @Autowired
  ImageService imageService;

  @PostMapping
  @ResponseBody
  public ResponseEntity<?> createProduct(@RequestBody Product product, @RequestParam("file") MultipartFile[] files) throws IOException {

    Long cateId = product.getCategory().getId();
    if(cateId == null){
      return new ResponseEntity<>(new ResponseMessange("Is required!"), HttpStatus.OK);
    }
//    Optional<Category> category = categoryService.findById(cateId);
//    if(product == null){
//      return new ResponseEntity<>(new ResponseMessange("maybe you are missing some data!"), HttpStatus.OK);
//    }
    if(product.getName().trim().isEmpty() || product.getCategory() == null ||
    product.getDescription().trim().isEmpty() || product.getPrice() == null ||
    product.getQuantity().intValue()==0 || product.getImageURL().trim().isEmpty() ||
    product.getPromotion_price() == null){
      return new ResponseEntity<>(new ResponseMessange("Is required!"), HttpStatus.OK);
    }
    //String fileName = ImageService.uploadFile(files[0]);
    productService.save(product);
    return new ResponseEntity<>(new ResponseMessange("Create product success!"), HttpStatus.OK);
  }


  @GetMapping
  @ResponseBody
  public ResponseEntity<?> getListProduct(){
    List<Product> products = productService.findAll();
    if(products.isEmpty()){
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(products , HttpStatus.OK);
  }
  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<?> getDetailProduct(@PathVariable Long id){
    Optional<Product> product = productService.findById(id);
    if(!product.isPresent()){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(product, HttpStatus.OK);
  }
  @PutMapping("/{id}")
  @ResponseBody
  public ResponseEntity<?> updateProduct(@RequestBody Product product,@PathVariable Long id){
    Optional<Product> product1 = productService.findById(id);
    if(!product1.isPresent()){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    if(product.getName().trim().isEmpty() || product.getCategory() == null ||
        product.getDescription().trim().isEmpty() || product.getPrice() == null ||
        product.getQuantity().intValue()==0 || product.getImageURL().trim().isEmpty() ||
        product.getPromotion_price() == null){
      return new ResponseEntity<>(new ResponseMessange("Is required!"), HttpStatus.OK);
    }
    product1.get().setName(product.getName());
    product1.get().setDescription(product.getDescription());
    product1.get().setPrice(product.getPrice());
    product1.get().setImageURL(product.getImageURL());
    product1.get().setQuantity(product.getQuantity());
    product1.get().setPromotion_price(product.getPromotion_price());
    product1.get().setCategory(product.getCategory());
    productService.save(product1.get());
    return new ResponseEntity<>(new ResponseMessange("Update success!"), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseBody
  public ResponseEntity<?> deleteProductById(@PathVariable Long id){
    Optional<Product> product = productService.findById(id);
    if(!product.isPresent()){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    productService.delete(product.get().getId());
    return new ResponseEntity<>( new ResponseMessange("Delete success!"), HttpStatus.OK);
  }

  @Autowired
  ICategoryRepository categoryRepository;
  @GetMapping("/edit/{id}")
  public String editProductById(@PathVariable Long id, Model model){
    Optional<Product> product = productService.findById(id);
    if(!product.isPresent()){
      return "404";
    }
    model.addAttribute("product",product.get());
    model.addAttribute("listOfCategory",categoryRepository.findAll());
    return "product_upload";
  }

  @PostMapping("/pic")
  public ResponseEntity<String> upload(@RequestParam("file") MultipartFile[] files) {
    for (MultipartFile file: files){
      try{
        String fileName = ImageService.uploadFile(file);
        String URIImage = ImageService.createURI(fileName);
        return new ResponseEntity<String>(URIImage, HttpStatus.OK);
      }catch (Exception e){

      }
    }
    return new ResponseEntity<String>("ok", HttpStatus.OK);
  }
}
