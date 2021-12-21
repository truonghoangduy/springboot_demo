package com.example.demo.demo;

import com.example.demo.demo.entities.Category;
import com.example.demo.demo.entities.Order;
import com.example.demo.demo.entities.OrderDetail;
import com.example.demo.demo.entities.Product;
import com.example.demo.demo.entities.User;
import com.example.demo.demo.repository.Category.ICategoryRepository;
import com.example.demo.demo.repository.Order.IOrderDetailRepository;
import com.example.demo.demo.repository.Order.IOrderRepository;
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

  @Autowired
  IOrderRepository orderRepository;

  @Autowired
  IOrderDetailRepository orderDetailRepository;

  Faker faker = new Faker();

  @Override
  public void run(String... args) throws Exception {
    if (orderRepository.count() == 0){
      seeder();
    }
    seeder();
  }


  private  void seeder(){
    var userList = seedUser();
    var categoryList = this.seedCategory(5);
    var productList = this.seedProduct(15,categoryList);
    this.seedOrder(userList,productList);



  }

  private List<User> seedUser(){
    List<User> userList = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      var user = new User();
      user.setName("springboot" + i);
      user.setId(Long.valueOf(i));
      user.setPassword("password");
      userList.add(user);
    }
    userList = userRepository.saveAll(userList);
    return  userList;
  }

  private void seedOrder(List<User> userList, List<Product> productList){
    for (int i = 0; i < userList.size(); i++) {
      var order = new Order();
      order.setUser(userList.get(i));
      var phone = String.valueOf(i);
      order.setPhone(("090"+phone));
      order.setShip_address(faker.address().streetAddress());

      order = this.orderRepository.save(order);
      var orderDetails = mockOrderDetail(productList,order);
//      order.setOrderDetails(orderDetails);
    }
  }

  private List<OrderDetail> mockOrderDetail(List<Product> productList, Order order){
    List<OrderDetail> orderDetails = new ArrayList<>();
    for (int i = 0; i < faker.random().nextInt(2,5); i++) {
      var orderDetail = new OrderDetail();
      orderDetail.setProduct(productList.get(faker.random().nextInt(0,productList.size()-1)));
      orderDetail.setQuantity(Long.valueOf(faker.random().nextInt(2,10)));
      orderDetail.setOrder(order);
      orderDetails.add(orderDetail);

    }
    orderDetails = orderDetailRepository.saveAll(orderDetails);
    return orderDetails;
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

  private List<Product>  seedProduct(int numberOfProductInDB, List<Category> categoryList){
    List<Product> productList = new ArrayList<>();
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
      productList.add(product);

    }
    productList =  this.productService.saveAll(productList);
    return productList;
  }
}
