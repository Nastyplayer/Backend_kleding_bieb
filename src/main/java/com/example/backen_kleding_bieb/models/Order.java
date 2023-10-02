package com.example.backen_kleding_bieb.models;


import com.example.backen_kleding_bieb.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String itemInfo;

    private LocalDate dateInfo;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getItemInfo() {
//        return itemInfo;
//    }
//
//    public void setItemInfo(String itemInfo) {
//        this.itemInfo = itemInfo;
//    }
//
//    public List<Item> getItems() {
//        return items;
//    }
//
//    public void setItems(List<Item> items) {
//        this.items = items;
//    }

//    public User getUser() {
//        return users;
//    }
//
    public void setUserDto(UserDto userDto) {
    }
    public UserDto getUserDto() {

        return new UserDto();
    }
//    public void setUser(User user) {
//        this.users = user;
//    }
//


    @JsonFormat(pattern = "dd-MM-yyyy")
    public LocalDate getDateInfo() {
        return dateInfo;
    }
    public void setDateInfo(LocalDate dateInfo) {
        this.dateInfo = dateInfo;
    }


    @OneToMany( fetch = FetchType.EAGER) //, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    List<Item> items;



    @ManyToOne
    private User users;


}



