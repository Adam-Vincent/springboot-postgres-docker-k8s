package com.practice.userservice.repositories.spec;

import com.practice.userservice.entities.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class Spec {

    private Spec(){}

    public static Specification<User> buildUserSpec(String name, String userId, String gender,Integer minAge,Integer maxAge) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();

            if (name != null) {
                Predicate p1 = criteriaBuilder.equal(root.get("name"), name);
                list.add(p1);
                Predicate p2 = criteriaBuilder.like(root.get("name").as(String.class), "%" + name + "%");
                list.add(p2);
            }
            if(userId != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("userId"), userId);
                list.add(predicate);
            }
            if(gender != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("gender"), gender);
                list.add(predicate);
            }
            if (minAge != 0 && maxAge != 0){
                Predicate predicate = criteriaBuilder.between(root.get("age").as(Integer.class), minAge, maxAge);
                list.add(predicate);
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
    }
}
