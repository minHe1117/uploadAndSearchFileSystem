package com.example.uploadfilesystem.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "uploads") // Assuming your table name is "uploads"
public class Upload {

    @Id
    private long skuId;

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private String size;

    @Column(name = "title")
    private String title;



    public Upload() {
    }

    public Upload(long skuId, String color, String size, String title) {
        this.skuId = skuId;
        this.color = color;
        this.size = size;
        this.title = title;
    }

    // Getters and setters
}
