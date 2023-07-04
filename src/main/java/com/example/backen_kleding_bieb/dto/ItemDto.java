package com.example.backen_kleding_bieb.dto;



import com.example.backen_kleding_bieb.models.Item;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter


public class ItemDto {

    @Id


    private Long id;

    private String nameInfo;

   // public String uploadFileName;


    List<Item.Tags> tags;


    @JsonIncludeProperties({"id", "username", "password", "apikey", "email"})
   // @JsonIgnore
    private User user;
   // private Object user;

    @JsonIncludeProperties({"id", "dateInfo", "itemInfo"})
    private Order order;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDto that = (ItemDto) o;
        return  Objects.equals(id, that.id) && Objects.equals(nameInfo, that.nameInfo)
                && Objects.equals(user, that.user) && Objects.equals(tags, that.tags) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameInfo, user, tags, order);
    }


}