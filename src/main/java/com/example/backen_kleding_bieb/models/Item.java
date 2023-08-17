
package com.example.backen_kleding_bieb.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter


@Entity
@Table(name = "items")
public class Item {


    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameInfo() {
        return nameInfo;
    }

    public void setNameInfo(String nameInfo) {
        this.nameInfo = nameInfo;
    }

    public Order getOrders() {
        return orders;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Column(unique = true, nullable = false)
    private String nameInfo;



    @ManyToOne
    @JsonIgnore

    private User user;



    public void setOrder(Order order) {
        this.orders = order;
    }


    @ManyToOne
    @JsonIgnore
    private Order orders;

    @OneToOne
    Upload uploads;



    public void setUpload(Upload photo) {
    }

    public Upload getUpload() {
        return uploads;
    }



    public enum Tags {

        SUSTAINABLE,
        BIOLOGICAL,
        ORGANIC,
        PESTICIDE_FREE,
        ADDITIVE_FREE,
        NON_CHEMICAL,
        WOOL_,
        LINEN_,
        LEATHER_,
        SILK_,
        COTTON_,
        MINIMALISTIC,
        ORGANISCH,
        WOL_,
        LINNEN_

    }



    @ElementCollection(targetClass = Tags.class)
    @Enumerated(EnumType.STRING)
    @Fetch(FetchMode.JOIN)
    List<Tags> tags;
}