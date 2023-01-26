package com.project.publisher.services.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@AllArgsConstructor
public class PostQuery extends PageableQuery {
    @Override
    public Sort sort(){
        return Sort.by("updated").descending();
    }
}
