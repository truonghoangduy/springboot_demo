package com.example.demo.demo.repository.Order;

import com.example.demo.demo.entities.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {

  Optional<Order> findOrderByPhone(String phone);
}
