package com.project.services.query;

import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public abstract class SortedQuery {
    private List<String> sortingFields;

    public Sort getSort() {
        List<SortingField> fields = this.getSortingFieldList();
        if (fields != null && !fields.isEmpty()) {
            Sort sort = Sort.unsorted();
            for (SortingField sortingField : fields) {
                sort = switch (sortingField.getOrder()) {
                    case ASC -> sort.and(Sort.by(sortingField.getFieldName()).ascending());
                    case DESC -> sort.and(Sort.by(sortingField.getFieldName()).descending());
                };
            }

            return sort;
        } else {
            return this.sort();
        }
    }

    public Sort sort() {
        return Sort.unsorted();
    }

    public List<SortingField> getSortingFieldList() {
        try {
            return this.sortingFields.stream().map((sortString) -> {
                String[] split = sortString.split("-");
                SortingField sortingField = new SortingField();
                sortingField.setFieldName(split[0]);
                sortingField.setOrder(Sort.Direction.fromString(split[1]));
                return sortingField;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    public void setSortingFields(List<String> sortingFields) {
        this.sortingFields = sortingFields;
    }
}
