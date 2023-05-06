package com.vavilov.tracker.tracker.service;

import com.vavilov.tracker.tracker.dto.GroupDto;
import com.vavilov.tracker.tracker.entity.GroupEntity;
import com.vavilov.tracker.tracker.entity.UserEntity;
import com.vavilov.tracker.tracker.exception.GroupAlreadyExistException;
import com.vavilov.tracker.tracker.exception.GroupIsNotExistException;
import com.vavilov.tracker.tracker.exception.UserIsNotExistException;
import com.vavilov.tracker.tracker.mapper.GroupMapper;
import com.vavilov.tracker.tracker.repository.GroupRepo;
import com.vavilov.tracker.tracker.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepo groupRepo;
    private final UserRepo userRepo;
    private final GroupMapper groupMapper;

    public GroupService(GroupRepo groupRepo, UserRepo userRepo, GroupMapper groupMapper) {
        this.groupRepo = groupRepo;
        this.groupMapper = groupMapper;
        this.userRepo = userRepo;
    }

    // Создание новой группы
    public GroupDto createGroup(String title, Long userID) throws GroupAlreadyExistException, UserIsNotExistException {
        if (groupRepo.findByTitle(title) != null) {
            throw new GroupAlreadyExistException("Group already exist");
        }

        Optional<UserEntity> user = userRepo.findById(userID);

        if (user.isEmpty()) {
            throw new UserIsNotExistException("User is not exist");
        }

        GroupEntity group = new GroupEntity(title, user.get());

        groupRepo.save(group);
        return groupMapper.toDto(group);

    }

    // Получение группы по ID
    public GroupDto getOneGroup(Long groupID) throws GroupIsNotExistException {
        Optional<GroupEntity> group = groupRepo.findById(groupID);
        if (group.isEmpty()) {
            throw new GroupIsNotExistException("Group is not exist");
        } else {
            return groupMapper.toDto(group.get());
        }
    }
}
