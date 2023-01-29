package com.project.publisher.services.specification;

import com.project.publisher.entities.Post;
import com.project.publisher.services.query.PostQuery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PostSpecificationBuilder implements SpecificationService<Post> {
    public Specification<Post> basicSpecification() {
        return this.eqNull("deleted");
    }

    public Specification<Post> searchPosts(PostQuery query){
        return this.basicSpecification()
                .and(iLikeSpecification(query.getHeadline(), "headline"))
                .and(inRange(query.getPostedDateMin(), query.getPostedDateMax(), "created"));
    }
}
