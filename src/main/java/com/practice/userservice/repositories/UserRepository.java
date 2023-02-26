package com.practice.userservice.repositories;

import com.practice.userservice.entities.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
@EntityScan(basePackages = {"com.practice.userservice.entities.User"})
public interface UserRepository extends CrudRepository<User,Long>, JpaSpecificationExecutor<User> {

}
