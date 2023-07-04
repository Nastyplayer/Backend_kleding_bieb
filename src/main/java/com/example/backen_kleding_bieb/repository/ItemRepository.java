package com.example.backen_kleding_bieb.repository;


import com.example.backen_kleding_bieb.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}