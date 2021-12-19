package com.example.demo.demo;

import com.example.demo.demo.entities.Category;
import com.example.demo.demo.entities.Product;
import com.example.demo.demo.entities.User;
import com.example.demo.demo.repository.Category.ICategoryRepository;
import com.example.demo.demo.repository.Product.IProductRepository;
import com.example.demo.demo.repository.User.IUserRepository;
import com.example.demo.demo.services.Category.CategoryServiceImpl;
import com.example.demo.demo.services.Product.ProductServiceImpl;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {

  @Autowired
  IProductRepository productService;

  @Autowired
  ICategoryRepository categoryService;

  @Autowired
  IUserRepository userRepository;

  Faker faker = new Faker();

  @Override
  public void run(String... args) throws Exception {
 //  seeder();
  }


  private  void seeder(){
    var userList = seedUser();
    var categoryList = this.seedCategory(5);
    this.seedProduct(15,categoryList);


  }

  private List<User> seedUser(){
    List<User> userList = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      var user = new User();
      user.setName("springboot" + i);
      user.setId(Long.valueOf(i));
      user.setPassword("password");
    }
    userList = userRepository.saveAll(userList);
    return  userList;
  }

  private List<Category> seedCategory(int numberOfCategory){
    List<Category> categoryList = new ArrayList<>();
    for (int i = 0; i < numberOfCategory; i++) {
      Category category = new Category();
      category.setDescription(faker.harryPotter().spell());
      category.setName(faker.commerce().department());
      categoryList.add(category);
    }
    categoryList = this.categoryService.saveAll(categoryList);
    return categoryList;
  }

  private void  seedProduct(int numberOfProductInDB, List<Category> categoryList){
    for (int i = 0; i < numberOfProductInDB; i++) {
      // select radom cat
      var selectedCat = categoryList.get(faker.random().nextInt(0,categoryList.size()-1));
      // mock product
      Product product = new Product();
      product.setDescription(faker.harryPotter().quote());
      product.setName(faker.commerce().productName());
      product.setPrice(Float.valueOf(faker.commerce().price()));
      product.setPromotion_price(Float.valueOf(faker.random().nextInt(10,20)));
      product.setCategory(selectedCat);

      this.productService.save(product);

    }
  }
}
