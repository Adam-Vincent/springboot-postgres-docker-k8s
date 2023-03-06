package com.practice.userservice.repositories;

import com.practice.userservice.entities.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EntityScan(basePackages = {"com.practice.userservice.entities.User"})
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    // 根据office的id查询user列表
    List<User> findByOfficeId(Long officeId);

    // 根据office的name查询user列表
}
