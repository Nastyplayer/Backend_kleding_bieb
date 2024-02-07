
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

    @ManyToOne
    @JoinColumn(name = "users_username")
    private User users;



    @Column(unique = true, nullable = false)
    private String nameInfo;



    public void setOrder(Order order) {
        this.orders = order;
    }


    @ManyToOne
    @JsonIgnore
    private Order orders;

//    @OneToOne
//    Upload uploads;
//


//    public void setUpload(Upload photo) {
//    }
//
//    public Upload getUpload() {
//        return uploads;
//    }



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