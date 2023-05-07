package com.vavilov.tracker.tracker.repository;

import com.vavilov.tracker.tracker.entity.GroupEntity;
import com.vavilov.tracker.tracker.entity.TimerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimerRepo extends CrudRepository<TimerEntity, Long> {

    List<TimerEntity> getAllByGroup(GroupEntity groupEntity);

    void deleteAllByGroup(GroupEntity groupEntity);
}
