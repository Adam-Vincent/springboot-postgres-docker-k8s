package com.practice.userservice.service;

import com.practice.userservice.entities.User;
import com.practice.userservice.exceptions.UserNotFoundException;
import com.practice.userservice.models.UserDto;
import com.practice.userservice.repositories.UserRepository;
import com.practice.userservice.repositories.spec.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmailServiceClient emailServiceClient;

    public UserService(UserRepository userRepository, EmailServiceClient emailServiceClient) {
        this.userRepository = userRepository;
        this.emailServiceClient = emailServiceClient;
    }


    public UserDto saveUser(UserDto userDto) {
        User userSaved = User.builder()
                .name(userDto.getName())
                .gender(userDto.getGender())
                .age(userDto.getAge())
                .userId(emailServiceClient.buildEmail(userDto.getUserId()))
                .build();
        userRepository.save(userSaved);
        return UserDto.toDto(userSaved);
    }

    public UserDto deleteUserById(Long id) throws UserNotFoundException {
        Optional<User> userDeleted;
        userDeleted = findUserById(id);
        if(userDeleted.isPresent()){
            userRepository.deleteById(id);
            return UserDto.toDto(userDeleted.get());
        }else {
            throw new UserNotFoundException("Could not find any users with ID "+id);
        }
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserDto updateUserById(Long id, UserDto userDto) throws UserNotFoundException {
        Optional<User> user = findUserById(id);
        if(user.isPresent()){
            userRepository.save(getUpdatedUser(userDto,user));
            return UserDto.toDto(getUpdatedUser(userDto,user));
        }
        else{
            throw new UserNotFoundException("Could not find any users with ID "+id);
        }
    }

    private static User getUpdatedUser(UserDto userDto, Optional<User> user) throws UserNotFoundException {
        if (user.isPresent()){
            user.get().setName(userDto.getName());
            user.get().setGender(userDto.getGender());
            user.get().setAge(userDto.getAge());
            user.get().setUserId(userDto.getUserId());
            return user.get();
        }
        else{
            throw new UserNotFoundException("Can not find user!");
        }
    }

    public Page<User> getPageList(String name,
                                  String userId,
                                  String gender,
                                  Integer minAge,
                                  Integer maxAge,
                                  Integer pageNum,
                                  Integer pageSize) {
        Specification<User> specification = Spec.buildUserSpec(name, userId, gender,minAge,maxAge);
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        return userRepository.findAll(specification,pageable);
    }

}
