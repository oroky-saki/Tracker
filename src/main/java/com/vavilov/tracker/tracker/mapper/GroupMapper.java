package com.vavilov.tracker.tracker.mapper;

import com.vavilov.tracker.tracker.dto.GroupDto;
import com.vavilov.tracker.tracker.entity.GroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel="spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface GroupMapper {

    GroupDto toDto(GroupEntity groupEntity);

    List<GroupDto> toDtoList(List<GroupEntity> groupEntities);
}
