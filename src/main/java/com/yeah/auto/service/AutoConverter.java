package com.yeah.auto.service;

import com.yeah.auto.dto.AutoDto;
import com.yeah.auto.entity.Auto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class AutoConverter {
    private final AutoGroupConverter autoGroupConverter;

    public Auto fromAutoDtoToAuto(AutoDto autoDto) {
        Auto auto = new Auto();
        auto.setId(autoDto.getId());
        auto.setPlate(autoDto.getPlate());
        auto.setMark(autoDto.getMark());
        auto.setModel(autoDto.getModel());
        auto.setAutoGroup(autoGroupConverter.fromAutoGroupDtoToAutoGroup(autoDto.getAutoGroupDto()));
        return auto;
    }

    public AutoDto fromAutoToAutoDto(Auto auto){
        return AutoDto.builder()
                .id(auto.getId())
                .plate(auto.getPlate())
                .mark(auto.getMark())
                .model(auto.getModel())
                .autoGroupDto(autoGroupConverter.fromAutoGroupToAutoGroupDto(auto.getAutoGroup()))
                .build();
    }
}
