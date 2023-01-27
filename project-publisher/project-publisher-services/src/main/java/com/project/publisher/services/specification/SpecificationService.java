package com.project.publisher.services.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface SpecificationService<T> {
    default Specification<T> eqSpecification(final Object object, String... paths) {
        if (object == null) {
            return isNullSpecification();
        }
        return (root, cq, cb) -> cb.equal(getObject(root, paths), object);
    }

    default Specification<T> likeSpecification(final Object object, String... paths) {
        if (object == null) {
            return isNullSpecification();
        }
        return (root, cq, cb) -> cb.like(getObject(root, paths), "%" + object + "%");
    }

    default Specification<T> iLikeSpecification(final String object, String... paths) {
        if (object == null) {
            return isNullSpecification();
        }
        return (root, cq, cb) -> cb.like(cb.lower(getObject(root, paths)), "%" + object.toLowerCase() + "%");
    }

    default Specification<T> distinct() {
        return (root, cq, cb) -> {
            cq.distinct(true);
            return cb.conjunction();
        };
    }

    default Specification<T> betweenSpecification(final String fieldName, final LocalDateTime from, final LocalDateTime to) {
        if (ObjectUtils.isEmpty(from) && ObjectUtils.isEmpty(to)) {
            return isNullSpecification();
        }
        if (ObjectUtils.isEmpty(from)) {
            return lessThanSpecification(to, fieldName);
        }
        if (ObjectUtils.isEmpty(to)) {
            return greaterThanSpecification(from, fieldName);
        }
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(getObject(root, fieldName), from, to));
    }

    default Specification<T> betweenSpecification(final String fieldName, final BigDecimal from, final BigDecimal to) {
        if (ObjectUtils.isEmpty(from) && ObjectUtils.isEmpty(to)) {
            return isNullSpecification();
        }
        if (ObjectUtils.isEmpty(from)) {
            return lessThanSpecification(to, fieldName);
        }
        if (ObjectUtils.isEmpty(to)) {
            return greaterThanSpecification(from, fieldName);
        }
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(getObject(root, fieldName), from, to));
    }


    default Specification<T> select(String... paths) {
        return (root, cq, cb) -> {

            List<Selection<?>> selections = new ArrayList<>();
            for (String path : paths) {
                selections.add(getObject(root, path));
            }
            cq.multiselect(selections);
            return cb.conjunction();
        };
    }

    default Specification<T> groupBy(String... paths) {
        return (root, cq, cb) -> {
            List<Expression<?>> groupBy = new ArrayList<>();
            for (String path : paths) {
                groupBy.add(getObject(root, path));
            }
            cq.groupBy(groupBy);
            return cb.conjunction();
        };
    }

    default Specification<T> inSpecification(final Collection<?> collection, String... paths) {
        if (collection == null) {
            return isNullSpecification();
        }
        return (root, cq, cb) -> getObject(root, paths).in(collection);
    }

    default Specification<T> startsWithIgnoreCase(final Object object, String... paths) {
        if (object == null) {
            return isNullSpecification();
        }
        return (root, cq, cb) -> cb.like(cb.lower(getObject(root, paths)), object + "%");
    }

    default <Y extends Comparable<? super Y>> Specification<T> lessThanEqSpecification(final Y to, String... paths) {
        if (to == null) {
            return isNullSpecification();
        }
        return (root, cq, cb) -> cb.lessThanOrEqualTo(getObject(root, paths), to);
    }

    default <Y extends Comparable<? super Y>> Specification<T> lessThanSpecification(final Y to, String... paths) {
        if (to == null) {
            return isNullSpecification();
        }
        return (root, cq, cb) -> cb.lessThan(getObject(root, paths), to);
    }

    default <Y extends Comparable<? super Y>> Specification<T> greaterThanSpecification(final Y from, String... paths) {
        if (from == null) {
            return isNullSpecification();
        }
        return (root, cq, cb) -> cb.greaterThanOrEqualTo(getObject(root, paths), from);
    }

    private Specification<T> isNullSpecification() {
        return (root, cq, cb) -> cb.conjunction();
    }

    default Specification<T> eqNull(String... paths) {
        return (root, cq, cb) -> cb.isNull(getObject(root, paths));
    }

    default Specification<T> notNull(String... paths) {
        return (root, cq, cb) -> cb.isNotNull(getObject(root, paths));
    }

    default <Y extends Comparable<? super Y>> Specification<T> inRange(Y lowBorder, Y maxBorder, String... paths) {
        if (lowBorder == null && maxBorder == null) {
            return isNullSpecification();
        }
        if (lowBorder == null) {
            return lessThanEqSpecification(maxBorder, paths);
        }
        if (maxBorder == null) {
            return greaterThanSpecification(lowBorder, paths);
        }
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(getObject(root, paths), lowBorder, maxBorder));
    }

    private <Y> Path<Y> getObject(Root<T> root, String... paths) {
        List<String> pathList = List.of(paths);
        Path<Y> result = root.get(pathList.get(0));
        for (String path : pathList.subList(1, pathList.size())) {
            result = result.get(path);
        }
        return result;
    }

    default <Y> Specification<T> fieldListInnerList(Collection<Y> filterList, String... paths) {
        if (filterList == null) {
            return isNullSpecification();
        }
        Specification<T> result = null;
        int i = 0;
        for (Y filter : filterList) {
            if (i == 0) {
                result = argumentInField(filter, paths);
            }
            result = result.and(argumentInField(filter, paths));
            i++;
        }
        return result;
    }

    default <Y> Specification<T> fieldInList(final Collection<Y> filterList, String... paths) {
        if (filterList == null) {
            return isNullSpecification();
        }
        return (root, cq, cb) -> getInFieldCriteria(filterList, cb, getObject(root, paths));
    }

    private <Y> CriteriaBuilder.In<Y> getInFieldCriteria(Collection<Y> filterList, CriteriaBuilder cb, Path<Y> path) {
        CriteriaBuilder.In<Y> inFieldCriteria = cb.in(path);
        for (Y id : filterList) {
            inFieldCriteria = inFieldCriteria.value(id);
        }
        return inFieldCriteria;
    }

    default <Y> Specification<T> argumentInField(Y filter, String... paths) {
        if (ObjectUtils.isEmpty(filter)) {
            return isNullSpecification();
        }
        return (root, cq, cb) -> {
            if (getObject(root, paths).getJavaType().isAssignableFrom(Collection.class)) {
                return cb.isMember(filter, getObject(root, paths));
            }
            throw new IllegalArgumentException("Argument is not a collection");
        };
    }
}
