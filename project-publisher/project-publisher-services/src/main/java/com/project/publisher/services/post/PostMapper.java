package com.project.publisher.services.post;

import com.project.publisher.entities.Post;
import com.project.publisher.repositories.PublisherRepository;
import com.project.publisher.request.PostRequest;
import com.project.publisher.response.PostResponse;
import com.project.publisher.services.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMapper implements Mapper<PostRequest, Post, PostResponse> {

    private final PublisherRepository publisherRepository;

    @Override
    public Post toEntity(PostRequest request) {
        Post post = new Post();
        post.setHeadline(request.getHeadline());
        post.setImage(request.getImage());
        post.setPublisher(this.publisherRepository.findByIdAndDeletedIsNull(request.getPublisherId()).orElseThrow());
        post.setBody(request.getBody());
        post.setLink(request.getLink());
        return post;
    }

    @Override
    public PostResponse toResponse(Post post) {
        PostResponse response = new PostResponse();
        response.setHeadline(post.getHeadline());
        response.setPublisher(post.getPublisher());
        response.setLink(post.getLink());
        response.setBody(post.getBody());
        response.setImage(post.getImage());
        return response;
    }
}
