package com.example.backen_kleding_bieb.repository;

import com.example.backen_kleding_bieb.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}