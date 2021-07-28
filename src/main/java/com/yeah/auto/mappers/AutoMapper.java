package com.yeah.auto.mappers;

import com.yeah.auto.dto.AutoDto;
import com.yeah.auto.dto.AutoGroupDto;
import com.yeah.auto.entity.Auto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AutoGroupMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AutoMapper {
    @Mapping(target = "autoGroupDto", source = "autoGroup")
    AutoDto mapToDto(Auto auto);
    @Mapping(target = "autoGroup", source = "autoGroupDto")
    Auto mapFromDto(AutoDto autoDto);

}
