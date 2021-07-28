package com.yeah.auto.mappers;

import com.yeah.auto.dto.AutoGroupDto;
import com.yeah.auto.entity.AutoGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutoGroupMapper {

    AutoGroupDto mapToDto(AutoGroup autoGroup);
    AutoGroup mapFromDto(AutoGroupDto autoGroupDto);


}
