package com.example.backen_kleding_bieb.repository;


import com.example.backen_kleding_bieb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

//@Repository
public interface UserRepository extends JpaRepository<User, String> {
//    User findByUsername(String username);
}
