package com.yeah.auto.service;

import com.yeah.auto.dto.AutoDto;
import com.yeah.auto.dto.AutoGroupDto;
import com.yeah.auto.entity.Auto;
import com.yeah.auto.entity.AutoGroup;
import com.yeah.auto.exception.ValidationException;
import com.yeah.auto.mappers.AutoGroupMapper;
import com.yeah.auto.mappers.AutoMapper;
import com.yeah.auto.repository.AutoGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
@Service
@RequiredArgsConstructor
public class DefaultAutoGroupService implements AutoGroupService {

    private final AutoGroupRepository autoGroupRepository;
//    private final AutoGroupConverter autoGroupConverter;
    private final AutoGroupMapper autoGroupMapper;
    private final AutoMapper autoMapper;

    @Override
    public AutoGroupDto addAutoGroup(AutoGroupDto autoGroupDto) throws ValidationException {
        validateAutoGroupDto(autoGroupDto);
        AutoGroup addedAutoGroup = autoGroupRepository.save(autoGroupMapper.mapFromDto(autoGroupDto));
        autoGroupDto.setId(addedAutoGroup.getId());
        return autoGroupMapper.mapToDto(addedAutoGroup);
    }

    private void validateAutoGroupDto(AutoGroupDto autoGroupDto) throws ValidationException {
        if (isNull(autoGroupDto)) {
            throw new ValidationException("Auto object is null");
        }
        if (isNull(autoGroupDto.getMark()) || autoGroupDto.getMark().isEmpty()){
            throw new ValidationException("mark is empty");
        }
    }

    @Override
    public List<AutoGroupDto> findAll() {
        return autoGroupRepository.findAll()
                .stream()
                .map(autoGroupMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AutoGroupDto findByMark(String mark) {
        AutoGroupDto autoGroupDto = autoGroupMapper.mapToDto(autoGroupRepository.findAutoGroupByMark(mark));
        return autoGroupDto;
    }
}
