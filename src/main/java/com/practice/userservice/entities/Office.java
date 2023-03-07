package com.practice.userservice.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor

@Entity
@Builder
@Table(name = "office_table",schema = "\"user\"")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String office;

    @OneToMany(mappedBy = "office")
    @ToString.Exclude
    private List<User> users;
}
