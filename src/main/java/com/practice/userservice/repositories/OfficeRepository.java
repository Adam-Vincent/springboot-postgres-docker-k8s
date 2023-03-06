package com.practice.userservice.repositories;

import com.practice.userservice.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office,Long> {

    // 根据user的id查询office信息
    Office findByUsersId(Long userId);

    // 根据user的name查询office信息
    Office findByUsersName(String userName);

    Office findByOffice(String office);
}
