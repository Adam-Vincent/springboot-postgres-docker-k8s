package com.practice.userservice.entities;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Builder
@MappedSuperclass
@Table(name = "user_table",schema = "\"user\"")
public class User extends EntityBase{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private String userId;

}
