package com.example.backen_kleding_bieb.models;


import com.example.backen_kleding_bieb.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonFormat;
//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor

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
//
    public void setUserDto(UserDto userDto) {
    }
    public UserDto getUserDto() {

        return new UserDto();
    }


    @JsonFormat(pattern = "dd-MM-yyyy")
    public LocalDate getDateInfo() {
        return dateInfo;
    }
    public void setDateInfo(LocalDate dateInfo) {
        this.dateInfo = dateInfo;
    }


    @OneToMany( fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    List<Item> items;



    @ManyToOne
    private User users;


}



