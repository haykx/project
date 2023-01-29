package com.project.publisher.services.post;

import com.project.publisher.entities.Post;
import com.project.publisher.entities.Publisher;
import com.project.publisher.repositories.PublisherRepository;
import com.project.publisher.request.PostRequest;
import com.project.publisher.response.PostResponse;
import com.project.publisher.services.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
@RequiredArgsConstructor
public class PostMapper implements Mapper<PostRequest, Post, PostResponse> {

    private final PublisherRepository publisherRepository;

    @Override
    public Post toEntity(PostRequest request) {
        Post post = new Post();
        post.setPublisher(this.getPublisher(request));
        post.setHeadline(request.getHeadline());
        post.setImage(request.getImage());
        post.setBody(request.getBody());
        post.setLink(request.getLink());
        post.setLikes(0);
        return post;
    }

    private Publisher getPublisher(PostRequest request) {
        return this.publisherRepository.findByIdAndDeletedIsNull(request.getPublisherId()).orElseThrow();
    }

    @Override
    public PostResponse toResponse(Post post) {
        PostResponse response = new PostResponse();
        response.setId(post.getId());
        response.setHeadline(post.getHeadline());
        response.setPublisherId(post.getPublisher().getId());
        response.setLink(post.getLink());
        response.setBody(post.getBody());
        if (post.getImage() != null) {
            response.setImage(new String(Base64.getEncoder().encode(post.getImage()), UTF_8));
        }
        response.setCreated(post.getCreated());
        response.setUpdated(post.getUpdated());
        response.setDeleted(post.getDeleted());
        return response;
    }
}
