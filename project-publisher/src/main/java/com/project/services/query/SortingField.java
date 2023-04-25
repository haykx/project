package com.project.services.query;

import org.springframework.data.domain.Sort;

public class SortingField {
    private String fieldName;
    private Sort.Direction order;

    public SortingField() {
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public Sort.Direction getOrder() {
        return this.order;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setOrder(Sort.Direction order) {
        this.order = order;
    }
}
