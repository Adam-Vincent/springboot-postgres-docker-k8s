package com.practice.userservice.repositories.spec;

import com.practice.userservice.entities.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class Spec {

    private Spec(){}

    public static Specification<User> buildUserSpec(String name, String userId, String gender,Integer minAge,Integer maxAge,String office) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            if (userId != null) {
                predicates.add(criteriaBuilder.equal(root.get("userId"), userId));
            }
            if (gender != null) {
                predicates.add(criteriaBuilder.equal(root.get("gender"), gender));
            }
            if (office != null) {
                predicates.add(criteriaBuilder.equal(root.get("office"), office));
            }
            if (minAge != null && maxAge != null) {
                predicates.add(criteriaBuilder.between(root.get("age").as(Integer.class), minAge, maxAge));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
