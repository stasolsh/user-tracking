package com.user.tracking.imperative.mapper;

import com.user.tracking.imperative.entity.Click;
import com.user.tracking.imperative.entity.ClickDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClickMapper {
    ClickDto toDto(Click click);

    @Mapping(target = "id", ignore = true)
    Click toEntity(ClickDto clickDto);
}
