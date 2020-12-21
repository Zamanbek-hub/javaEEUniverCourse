package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="comment", length=500)
    private String comment;

    public Comment(String comment, Date addedDate, Item item, Users author) {
        this.comment = comment;
        this.addedDate = addedDate;
        this.item = item;
        this.author = author;
    }

    @Column(name = "addedDate")
    private Date addedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users author;

    public Comment(String comment, Item item, Users author) {
        this.comment = comment;
        this.item = item;
        this.author = author;
    }
}
