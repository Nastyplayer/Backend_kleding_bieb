package com.example.backen_kleding_bieb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

/*annotatie   DONE!!!!*/
@Entity
@Table(name = "items")
public class Item {

    @Id

  @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column
//    @GeneratedValue(generator = "sequence-generator")
//    @GenericGenerator(
//            name = "sequence-generator",
//            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//            parameters = {
//                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
//                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1003"),
//                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
//            }
//            )
 private Long id;

    private String nameInfo;

   // public String uploadFileName;

    @ManyToOne
    @JsonIgnore
 //  @JoinColumn(name = "user")
    private User user;

    @ManyToOne
   // @JoinColumn(name = "item_id")
    private Order order;

    public void setUpload(Upload photo) {
    }

    @OneToOne
    Upload upload;
    public enum Tags {

        SUSTAINABLE,
        BIOLOGICAL,
        ORGANIC,
        PESTICIDE_FREE,
        ADDITIVE_FREE,
        NON_CHEMICAL,
        WOOL,
        LINEN,
        SILK,
        COTTON,
        MINIMALISTIC,
        ORGANISCH,
        WOL,
        LINNEN

    }

    @ElementCollection(targetClass = Tags.class)
    //@LazyCollection
    @Enumerated(EnumType.STRING)
    List<Tags> tags;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item1 = (Item) o;
        return  Objects.equals(id, item1.id) && Objects.equals(nameInfo, item1.nameInfo)
                && Objects.equals(user, item1.user)&& Objects.equals(order, item1.order)
                && Objects.equals(tags, item1.tags); //&& Objects.equals(uploadFileName, item1.uploadFileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameInfo, user, order, tags);//,uploadFileName );
    }

}


///Objects.equals(id, item1.id) &&