package com.example.demo.demo.repository.User;

import com.example.demo.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {

}
