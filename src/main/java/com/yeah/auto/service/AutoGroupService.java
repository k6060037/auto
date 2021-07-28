package com.yeah.auto.service;

import com.yeah.auto.dto.AutoDto;
import com.yeah.auto.dto.AutoGroupDto;
import com.yeah.auto.exception.ValidationException;

import java.util.List;
import java.util.UUID;

public interface AutoGroupService {
    AutoGroupDto addAutoGroup(AutoGroupDto autoGroupDto) throws ValidationException;
    List<AutoGroupDto> findAll();
    AutoGroupDto findByMark(String mark);

}
