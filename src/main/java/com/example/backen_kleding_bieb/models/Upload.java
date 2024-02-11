package com.example.backen_kleding_bieb.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor


@Getter
@Setter

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


}