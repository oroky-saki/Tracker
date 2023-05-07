package com.vavilov.tracker.tracker.service;

import com.vavilov.tracker.tracker.dto.GroupDto;
import com.vavilov.tracker.tracker.entity.GroupEntity;
import com.vavilov.tracker.tracker.entity.UserEntity;
import com.vavilov.tracker.tracker.exception.GroupAlreadyExistException;
import com.vavilov.tracker.tracker.mapper.GroupMapper;
import com.vavilov.tracker.tracker.repository.GroupRepo;
import com.vavilov.tracker.tracker.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
    public GroupDto createGroup(String title, Long userID) throws GroupAlreadyExistException, NoSuchElementException {
        if (groupRepo.findByTitle(title) != null) {
            throw new GroupAlreadyExistException("Group already exist");
        }

        Optional<UserEntity> user = userRepo.findById(userID);
        user.orElseThrow();

        GroupEntity group = new GroupEntity(title, user.get());
        groupRepo.save(group);

        return groupMapper.toDto(group);

    }

    // Возможно стоит удалить
    // Получение группы по ID
    public GroupDto getOneGroup(Long groupID) throws NoSuchElementException{
        Optional<GroupEntity> group = groupRepo.findById(groupID);
        group.orElseThrow();
        return groupMapper.toDto(group.get());
    }

    public void deleteGroup(Long groupID) throws NoSuchElementException{
        Optional<GroupEntity> group = groupRepo.findById(groupID);
        group.orElseThrow();
        groupRepo.deleteById(groupID);
    }

    public void patchBandsTitle(Long groupID, String newTitle) throws NoSuchElementException {
        Optional<GroupEntity> group = groupRepo.findById(groupID);
        group.orElseThrow();
        group.get().setTitle(newTitle);
        groupRepo.save(group.get());
    }

    public List<GroupDto> getAllGroupsByUser(Long userID) throws NoSuchElementException {
        Optional<UserEntity> user = userRepo.findById(userID);
        user.orElseThrow();
        return groupMapper.toDtoList(groupRepo.findAllByUser(user.get()));
    }
}
