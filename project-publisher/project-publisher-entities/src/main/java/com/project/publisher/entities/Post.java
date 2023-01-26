package com.project.publisher.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "t_posts")
public class Post extends BaseEntity {

    @Column(name = "headline")
    private String headline;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "body")
    private String body;

    @Column(name = "link")
    private URL link;

    @ManyToOne(optional = false)
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    //TODO: discuss the possibility of having comments and/or likes

}
