package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pictures")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Picture {
    public Picture(String url, Item item) {
        this.url = url;
        this.item = item;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="url", length=500)
    private String url;

    @Column(name = "date")
    Date addedDate;

    public Picture(String url, Date addedDate, Item item) {
        this.url = url;
        this.addedDate = addedDate;
        this.item = item;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

}
