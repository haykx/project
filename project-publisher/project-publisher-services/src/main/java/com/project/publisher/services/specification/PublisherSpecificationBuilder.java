package com.project.publisher.services.specification;

import com.project.publisher.entities.Publisher;
import com.project.publisher.services.exceptions.BadRequestException;
import com.project.publisher.services.query.PublisherQuery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PublisherSpecificationBuilder implements SpecificationService<Publisher> {
    public Specification<Publisher> basicSpecification() {
        return this.eqNull("deleted");
    }

    public Specification<Publisher> searchPublishers(PublisherQuery query){
        String[] s = query.getFullName().split(" ");
        if(s.length == 2){
            return this.basicSpecification()
                    .and(iLikeSpecification(s[0], "first_name"))
                    .and(iLikeSpecification(s[1], "last_name"))
                    .or(iLikeSpecification(s[1], "first_name"))
                    .and(iLikeSpecification(s[0], "last_name"));
        } else if (s.length == 1) {
            return this.basicSpecification()
                    .and(iLikeSpecification(s[0], "first_name"))
                    .or(iLikeSpecification(s[0], "last_name"));
        } else {
            throw new BadRequestException("Invalid search parameters");
        }
    }
}
