package com.vavilov.tracker.tracker.service;

import com.vavilov.tracker.tracker.dto.TimerDto;
import com.vavilov.tracker.tracker.entity.GroupEntity;
import com.vavilov.tracker.tracker.entity.TimerEntity;
import com.vavilov.tracker.tracker.mapper.TimerMapper;
import com.vavilov.tracker.tracker.repository.GroupRepo;
import com.vavilov.tracker.tracker.repository.TimerRepo;
import com.vavilov.tracker.tracker.utils.TimerUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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

    @Transactional
    public TimerDto createTimer(String title, Long groupID) throws NoSuchElementException {
        Optional<GroupEntity> group = groupRepo.findById(groupID);
        group.orElseThrow();
        Date start_time = new Date();
        TimerEntity timer = new TimerEntity(title, start_time, 0, 0, "default", group.get());
        return timerMapper.toDto(timerRepo.save(timer));

    }

    @Transactional
    public TimerDto getOneTimer(Long timerID) throws NoSuchElementException {
        Optional<TimerEntity> timer = timerRepo.findById(timerID);
        timer.orElseThrow();
        return timerMapper.toDto(timer.get());
    }

    @Transactional
    public List<TimerDto> getTimersByGroup(Long groupID) throws NoSuchElementException {
        Optional<GroupEntity> group = groupRepo.findById(groupID);
        group.orElseThrow();
        return timerMapper.toDtoList(timerRepo.getAllByGroup(group.get()));
    }

    @Transactional
    public void deleteTimer(Long timerID) throws NoSuchElementException {
        Optional<TimerEntity> timer = timerRepo.findById(timerID);
        timer.orElseThrow();
        timerRepo.delete(timer.get());
    }

    @Transactional
    public void deleteTimersByGroup(Long groupID) throws NoSuchElementException {
        Optional<GroupEntity> group = groupRepo.findById(groupID);
        group.orElseThrow();
        timerRepo.deleteAllByGroup(group.get());
    }

    @Transactional
    public TimerDto changeTimerTitle(Long timerID, String title) throws NoSuchElementException {
        Optional<TimerEntity> timer = timerRepo.findById(timerID);
        timer.orElseThrow();
        timer.get().setTitle(title);
        return timerMapper.toDto(timerRepo.save(timer.get()));
    }

    @Transactional
    public TimerDto changeTimerStatus(Long timerID, String newStatus) throws NoSuchElementException {
        Optional<TimerEntity> timer = timerRepo.findById(timerID);
        timer.orElseThrow();

        if (timer.get().getStatus().equals("stop")) {
            return timerMapper.toDto(timer.get());
        }

        TimerEntity updatedTimer;

        if (newStatus.equals("run")){
            updatedTimer = TimerUtil.runTimer(timer.get());
        } else if (newStatus.equals("pause")) {
            updatedTimer = TimerUtil.pauseTimer(timer.get());
        } else if (newStatus.equals("stop")) {
            updatedTimer = TimerUtil.stopTimer(timer.get(), timer.get().getStatus());
        } else {
            updatedTimer = TimerUtil.runTimer(timer.get());
        }

        return timerMapper.toDto(timerRepo.save(updatedTimer));
    }

}
