package com.vavilov.tracker.tracker.service;

import com.vavilov.tracker.tracker.dto.TimerDto;
import com.vavilov.tracker.tracker.entity.GroupEntity;
import com.vavilov.tracker.tracker.entity.TimerEntity;
import com.vavilov.tracker.tracker.mapper.TimerMapper;
import com.vavilov.tracker.tracker.repository.GroupRepo;
import com.vavilov.tracker.tracker.repository.TimerRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TimerService {

    private final GroupRepo groupRepo;
    private final TimerRepo timerRepo;
    private final TimerMapper timerMapper;

    public TimerService(GroupRepo groupRepo, TimerRepo timerRepo, TimerMapper timerMapper) {
        this.groupRepo = groupRepo;
        this.timerRepo = timerRepo;
        this.timerMapper = timerMapper;
    }

    public TimerDto createTimer(String title, Long groupID) throws NoSuchElementException {
        Optional<GroupEntity> group = groupRepo.findById(groupID);
        group.orElseThrow();
        Date start_time = new Date();
        TimerEntity timer = new TimerEntity(title, start_time, 0, 0, "stop", group.get());
        return timerMapper.toDto(timerRepo.save(timer));

    }
}
