package com.vavilov.tracker.tracker.service;

import com.vavilov.tracker.tracker.dto.UserDto;
import com.vavilov.tracker.tracker.entity.UserEntity;
import com.vavilov.tracker.tracker.exception.InvalidEmailException;
import com.vavilov.tracker.tracker.exception.UserAlreadyExistException;
import com.vavilov.tracker.tracker.mapper.UserMapper;
import com.vavilov.tracker.tracker.repository.UserRepo;
import com.vavilov.tracker.tracker.utils.EmailUtil;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final EmailUtil emailUtil;

    public UserService(UserRepo userRepo, UserMapper userMapper, EmailUtil emailUtil) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.emailUtil = emailUtil;
    }

    public UserDto createUser(String email, String password) throws UserAlreadyExistException, InvalidEmailException {
        // Проверка валидности адреса электронной почты
        if (EmailUtil.validEmail(email) == false)
            throw new InvalidEmailException("Invalid email address");

        if (userRepo.findByEmail(email) != null) {
            throw new UserAlreadyExistException("User already exist");
        }

        UserEntity user = new UserEntity(email, password, "user");

        userRepo.save(user);
        return userMapper.toDto(user);
    }
}
