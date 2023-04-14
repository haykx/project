package com.project.publisher.services.specification;

import com.project.publisher.entities.Survey;
import com.project.publisher.services.query.SurveyQuery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class SurveySpecificationBuilder implements SpecificationService<Survey> {
    public Specification<Survey> searchPosts(final SurveyQuery query) {
        return this
                .iLikeSpecification(query.getTitle(), "title")
                .and(inRange(query.getPostedDateMin(), query.getPostedDateMax(), "created"));
    }
}
