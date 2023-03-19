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
@Table(name = "t_comments")
public class Comment extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @ManyToOne
    @JoinColumn(name = "discussion_id")
    private Discussion discussion;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "likes", nullable = false)
    private int likes;

    @ElementCollection
    @CollectionTable(name="t_like_comment", joinColumns=@JoinColumn(name="comment_id"))
    @Column(name="publisher_id")
    private List<UUID> likers = new ArrayList<>();
    @OneToMany(mappedBy = "parent")
    private List<Comment> replies = new ArrayList<>();

    public void like(UUID pubId) {
        this.likes++;
        this.likers.add(pubId);
    }

    public void unlike(UUID pubId) {
        this.likes--;
        this.likers.remove(pubId);
    }
}