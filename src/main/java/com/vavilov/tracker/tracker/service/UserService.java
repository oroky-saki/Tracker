package com.vavilov.tracker.tracker.service;

import com.vavilov.tracker.tracker.dto.UserDto;
import com.vavilov.tracker.tracker.entity.UserEntity;
import com.vavilov.tracker.tracker.exception.UserAlreadyExistException;
import com.vavilov.tracker.tracker.mapper.UserMapper;
import com.vavilov.tracker.tracker.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public UserService(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    public UserDto createUser(String email, String password) throws UserAlreadyExistException{
        if (userRepo.findByEmail(email) != null) {
            throw new UserAlreadyExistException("User already exist");
        }

        UserEntity user = new UserEntity(email, password, "user");

        userRepo.save(user);
        return userMapper.toDto(user);
    }
}
