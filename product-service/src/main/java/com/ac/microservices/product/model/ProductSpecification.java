package com.ac.microservices.product.model;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductSpecification implements Specification<Product> {
    private ProductFilterDto criteria;

    public ProductSpecification(ProductFilterDto criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        final List<Predicate> predicates = new ArrayList<>();

        if (criteria.getIds() != null) {
            String[] list = criteria.getIds().split(",");

            List<String> ids = new ArrayList<>(Arrays.asList(list));

            if(criteria.getIds()!=null) {
                Expression<String> exp = root.get("id");
                Predicate predicate = exp.in(ids);
                predicates.add(predicate);
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
