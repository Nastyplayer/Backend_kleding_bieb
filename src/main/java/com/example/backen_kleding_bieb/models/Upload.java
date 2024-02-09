package com.example.backen_kleding_bieb.models;


//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
//@AllArgsConstructor

@Getter
@Setter
//
@Entity
@Table(name = "uploads")

public class Upload {
    @Id
    private String fileName;
    private String textType;
    private String url;

    public Upload(String fileName, String textType, String url) {
        this.fileName = fileName;
        this.textType = textType;
        this.url = url;

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTextType() {
        return textType;
    }

    public void setTextType(String textType) {
        this.textType = textType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



//    public void setAccount(Account account) {
//    }
//
//    public void setItem(Item item) {
//    }
//
//
//
//  ////////////////////////////////////////////
//
//    @OneToOne(orphanRemoval = true)
//    @JoinTable(name = "uploads_item",
//            joinColumns = @JoinColumn(name = "uploads_file_name"),
//            inverseJoinColumns = @JoinColumn(name = "item_id"))
//    private Item item;
//
//    //////////////////////////////////////////////////////
//    @OneToOne(orphanRemoval = true)
//    @JoinTable(name = "uploads_account",
//            joinColumns = @JoinColumn(name = "uploads_file_name"),
//            inverseJoinColumns = @JoinColumn(name = "account_id"))
//    private Account account;
}