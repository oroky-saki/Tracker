package com.vavilov.tracker.tracker.repository;

import com.vavilov.tracker.tracker.entity.GroupEntity;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepo extends CrudRepository<GroupEntity, Long> {
    GroupEntity findByTitle(String title);
}
