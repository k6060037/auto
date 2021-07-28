package com.yeah.auto.service;

import com.yeah.auto.dto.AutoDto;
import com.yeah.auto.dto.AutoGroupDto;
import com.yeah.auto.exception.ValidationException;

import java.util.UUID;
import java.util.List;


public interface AutoService {
    AutoDto addAuto(AutoDto autoDto) throws ValidationException;
    void deleteAuto(UUID id);
    AutoDto findByPlate(String plate);
    List<AutoDto> findAll();
    List<AutoDto> findAllByMark(String mark);

}
