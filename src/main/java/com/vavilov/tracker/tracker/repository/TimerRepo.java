package com.vavilov.tracker.tracker.repository;

import com.vavilov.tracker.tracker.entity.TimerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimerRepo extends CrudRepository<TimerEntity, Long> {

}
