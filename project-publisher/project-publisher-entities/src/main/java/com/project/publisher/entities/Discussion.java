package com.project.publisher.entities;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "t_discussions")
public class Discussion extends Publication {

    @Column(name = "question")
    private String question;

    @Column(name = "body")
    private String body;

    @Column(name = "link")
    private String link;

    @Column(name = "likes")
    private int likes;

    @ElementCollection
    @CollectionTable(name="t_like_discussion", joinColumns=@JoinColumn(name="discussion_id"))
    @Column(name="publisher_id")
    private List<UUID> likers = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @OneToMany(mappedBy = "discussion")
    private List<Comment> comments = new ArrayList<>();

    public void like(UUID pubId) {
        this.likes++;
        this.likers.add(pubId);
    }

    public void unlike(UUID pubId) {
        this.likes--;
        this.likers.remove(pubId);
    }
}
