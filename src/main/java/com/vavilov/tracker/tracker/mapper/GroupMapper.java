package com.vavilov.tracker.tracker.mapper;

import com.vavilov.tracker.tracker.dto.GroupDto;
import com.vavilov.tracker.tracker.entity.GroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel="spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface GroupMapper {

    GroupDto toDto(GroupEntity groupEntity);
}
