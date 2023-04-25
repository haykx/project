package com.project.services.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@AllArgsConstructor
public class PublisherQuery extends PageableQuery {
    private String fullName;
    @Override
    public Sort sort(){
        return Sort.by("updated").descending();
    }
}
