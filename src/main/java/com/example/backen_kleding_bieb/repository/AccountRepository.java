package com.example.backen_kleding_bieb.repository;



import com.example.backen_kleding_bieb.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}