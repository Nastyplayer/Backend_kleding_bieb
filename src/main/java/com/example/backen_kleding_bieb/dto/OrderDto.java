package com.example.backen_kleding_bieb.dto;


import com.example.backen_kleding_bieb.models.Item;
import com.example.backen_kleding_bieb.models.User;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
//import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;



@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter


public class OrderDto {

    @Id
    private Long id;

    private String itemInfo;

    private LocalDate dateInfo;


        public User getUsers() {
    return null;
}
    @JsonIncludeProperties({"id", "nameInfo"})
    private List<Item> items;


    @JsonIncludeProperties({"username"})
    private UserDto userDto;

}