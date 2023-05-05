package com.vavilov.tracker.tracker.mapper;

import com.vavilov.tracker.tracker.dto.UserDto;
import com.vavilov.tracker.tracker.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel="spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {

    UserDto toDto(UserEntity userEntity);
}
