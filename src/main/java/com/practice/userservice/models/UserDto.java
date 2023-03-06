package com.practice.userservice.models;

import com.practice.userservice.entities.Office;
import com.practice.userservice.entities.User;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "性别不能为空")
    private String gender;

    @Min(0)
    @NotNull(message = "年龄不能为空")
    private Integer age;

    @NotBlank(message = "userId不能为空")
    private String userId;

    @NotBlank
    private String office;


    public static UserDto toDto(@NotNull User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .gender(entity.getGender())
                .age(entity.getAge())
                .userId(entity.getUserId())
                .build();
    }
}
