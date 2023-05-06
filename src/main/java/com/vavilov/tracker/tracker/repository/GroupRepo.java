package com.vavilov.tracker.tracker.repository;

import com.vavilov.tracker.tracker.entity.GroupEntity;
import com.vavilov.tracker.tracker.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepo extends CrudRepository<GroupEntity, Long> {
    GroupEntity findByTitle(String title);

    List<GroupEntity> findAllByUser(UserEntity user);
}
