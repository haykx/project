package com.project.services.specification;

import com.project.entities.model.Survey;
import com.project.services.query.SurveyQuery;
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
