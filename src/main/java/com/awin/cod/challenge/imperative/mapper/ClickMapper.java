package com.awin.cod.challenge.imperative.mapper;

import com.awin.cod.challenge.imperative.entity.Click;
import com.awin.cod.challenge.imperative.entity.ClickDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClickMapper {
    ClickDto toDto(Click click);
    @Mapping(target = "id", ignore = true)
    Click toEntity(ClickDto clickDto);
}
