package com.practice.userservice.repositories;

import com.practice.userservice.entities.Office;
import com.practice.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    // 根据office的id查询user列表
    List<User> findByOfficeId(Long officeId);

    // 根据office的name查询user列表
    List<User> findByOffice(String office);

    @Modifying
    @Query("update User u set u.name = :name, u.age = :age, u.gender = :gender, " +
           "u.userId = :userId,u.office = :office where u.id = :id")
    void updateUser(@Param("id") Long id, @Param("name") String name,
                    @Param("age") Integer age, @Param("gender") String gender,
                    @Param("userId") String userId);

    @Query("select o from Office o where o.id = :id")
    Office findOfficeById(@Param("id") Long id);
}
