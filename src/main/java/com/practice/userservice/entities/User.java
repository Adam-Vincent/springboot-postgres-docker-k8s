package com.practice.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.practice.userservice.models.UserDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor

@Entity
@Builder
@Table(name = "user_table",schema = "\"user\"")
public class User extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "userid")
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JoinColumn(name = "office_id",referencedColumnName = "id")
    @ToString.Exclude
    private Office office;

    public void patch(UserDto userDto) {
        if (userDto.getName() != null) {
            this.name = userDto.getName();
        }
        if (userDto.getAge() != null) {
            this.age = userDto.getAge();
        }
        if (userDto.getGender() != null) {
            this.gender = userDto.getGender();
        }
        if (userDto.getUserId() != null) {
            this.userId = userDto.getUserId();
        }
        if (userDto.getOffice() != null) {
            if (this.office == null) {
                this.office = new Office();
            }
            this.office.setOffice(userDto.getOffice());
        }
    }
}
