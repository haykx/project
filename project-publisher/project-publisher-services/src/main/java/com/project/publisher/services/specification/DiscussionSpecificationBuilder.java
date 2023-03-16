package com.project.publisher.services.specification;

import com.project.publisher.entities.Discussion;
import com.project.publisher.services.query.DiscussionQuery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class DiscussionSpecificationBuilder implements SpecificationService<Discussion> {
    public Specification<Discussion> searchPosts(final DiscussionQuery query){
        return this
                .iLikeSpecification(query.getQuestion(), "question")
                .and(inRange(query.getPostedDateMin(), query.getPostedDateMax(), "created"));
    }
}
