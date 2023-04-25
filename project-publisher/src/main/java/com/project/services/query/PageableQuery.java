package com.project.services.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public abstract class PageableQuery extends SortedQuery {
    protected Integer page = 0;
    protected Integer size = 15;

    public Pageable getPageable(){
        return PageRequest.of(this.page,this.size,this.getSort());
    }
}
