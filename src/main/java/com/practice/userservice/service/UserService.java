package com.practice.userservice.service;

import com.practice.userservice.entities.Office;
import com.practice.userservice.entities.User;
import com.practice.userservice.exceptions.UserNotFoundException;
import com.practice.userservice.models.UserDto;
import com.practice.userservice.repositories.OfficeRepository;
import com.practice.userservice.repositories.UserRepository;
import com.practice.userservice.repositories.spec.Spec;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final OfficeRepository officeRepository;
    private final EmailServiceClient emailServiceClient;

    public UserService(ModelMapper modelMapper, UserRepository userRepository, OfficeRepository officeRepository, EmailServiceClient emailServiceClient) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.officeRepository = officeRepository;
        this.emailServiceClient = emailServiceClient;
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto saveUser(UserDto userDto) {
        User newUser = modelMapper.map(userDto,User.class);
        Office office = officeRepository.findByOffice(userDto.getOffice()); //根据UserDto中的office属性查询数据库中是否存在对应的Office实体类

        if (office == null) { //如果不存在，则创建一个新的Office实体类，并保存到数据库中
            office = new Office();
            office.setOffice(userDto.getOffice());
            officeRepository.save(office);
        }
        newUser.setOffice(office);

        userRepository.save(newUser);
        return modelMapper.map(newUser,UserDto.class);
    }

    public UserDto deleteUserById(Long id) throws EntityNotFoundException {
        Optional<User> userDeleted;
        userDeleted = findUserById(id);
        if(userDeleted.isPresent()){
            userRepository.deleteById(id);
            return modelMapper.map(userDeleted,UserDto.class);
        }else {
            throw new EntityNotFoundException("User not found");
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
