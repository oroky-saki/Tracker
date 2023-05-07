package com.vavilov.tracker.tracker.mapper;

import com.vavilov.tracker.tracker.dto.TimerDto;
import com.vavilov.tracker.tracker.entity.TimerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel="spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TimerMapper {

    TimerDto toDto(TimerEntity timerEntity);

    List<TimerDto> toDtoList(List<TimerEntity> timerEntities);
}
