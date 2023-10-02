package com.example.backen_kleding_bieb.dto;

import com.example.backen_kleding_bieb.models.Item;
import com.example.backen_kleding_bieb.models.Upload;
import com.example.backen_kleding_bieb.models.User;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class ItemDto {


    @Id


    private Long id;

    private String nameInfo;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getNameInfo() {
//        return nameInfo;
//    }
//
//    public void setNameInfo(String nameInfo) {
//        this.nameInfo = nameInfo;
//    }
//
//    public List<Item.Tags> getTags() {
//        return tags;
//    }
//
//    public void setTags(List<Item.Tags> tags) {
//        this.tags = tags;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public List<Order> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(List<Order> orders) {
//        this.orders = orders;
//    }
//
//
//
//
//    public Upload getUpload() {
//        return upload;
//    }
//
//    public void setUpload(Upload upload) {
//        this.upload = upload;
//    }
//

    List<Item.Tags> tags;

    @JsonIncludeProperties({"url", "fileName", "textType"})
    private Upload upload;

    @JsonIncludeProperties({"username", "password", "email"})

    private User user;


    @JsonIncludeProperties({"id", "dateInfo", "itemInfo"})
    private List<Order> orders;


}