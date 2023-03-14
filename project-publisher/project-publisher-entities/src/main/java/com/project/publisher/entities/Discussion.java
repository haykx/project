package com.project.publisher.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "t_discussions")
public class Discussion extends BaseEntity {

    @Column(name = "question")
    private String question;

    @Column(name = "body")
    private String body;

    @Column(name = "link")
    private String link;

    @Column(name = "likes")
    private int likes;

    @ManyToOne(optional = false)
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @OneToMany(mappedBy = "discussion")
    private List<Comment> comments;
}
