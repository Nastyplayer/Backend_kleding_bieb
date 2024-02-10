package com.example.backen_kleding_bieb.dto;

import com.example.backen_kleding_bieb.models.Item;
import com.example.backen_kleding_bieb.models.Order;
import com.example.backen_kleding_bieb.models.User;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class ItemDto {


    @Id

    private Long id;

    private String nameInfo;


    List<Item.Tags> tags;



    @JsonIncludeProperties({"username", "password", "email"})

    private User user;


    @JsonIncludeProperties({"id", "dateInfo", "itemInfo"})
    private List<Order> orders;


}