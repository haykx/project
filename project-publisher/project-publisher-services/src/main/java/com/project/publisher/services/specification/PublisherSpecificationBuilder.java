package com.project.publisher.services.specification;

import com.project.publisher.entities.Publisher;
import com.project.publisher.services.query.PublisherQuery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PublisherSpecificationBuilder implements SpecificationService<Publisher> {
    public Specification<Publisher> basicSpecification() {
        return this.eqNull("deleted");
    }

    public Specification<Publisher> searchPublishers(PublisherQuery query){
        return this.basicSpecification()
                .and(iLikeSpecification(query.getFullName(), "first_name"+"last_mame"));
    }
}
