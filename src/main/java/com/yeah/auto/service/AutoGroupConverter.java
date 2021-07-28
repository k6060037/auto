package com.yeah.auto.service;

import com.yeah.auto.dto.AutoDto;
import com.yeah.auto.dto.AutoGroupDto;
import com.yeah.auto.entity.Auto;
import com.yeah.auto.entity.AutoGroup;
import org.springframework.stereotype.Component;

@Component
public class AutoGroupConverter {
    public AutoGroup fromAutoGroupDtoToAutoGroup(AutoGroupDto autoGroupDto) {
        AutoGroup autoGroup = new AutoGroup();
        autoGroup.setId(autoGroupDto.getId());
        autoGroup.setMark(autoGroupDto.getMark());
        return autoGroup;
    }

    public AutoGroupDto fromAutoGroupToAutoGroupDto(AutoGroup autoGroup){
        return AutoGroupDto.builder()
                .id(autoGroup.getId())
                .mark(autoGroup.getMark())
                .build();
    }
}

