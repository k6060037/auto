package com.yeah.auto.service;

import com.yeah.auto.dto.AutoDto;
import com.yeah.auto.dto.AutoGroupDto;
import com.yeah.auto.entity.Auto;
import com.yeah.auto.entity.AutoGroup;
import com.yeah.auto.exception.ValidationException;
import com.yeah.auto.mappers.AutoGroupMapper;
import com.yeah.auto.mappers.AutoMapper;
import com.yeah.auto.repository.AutoGroupRepository;
import com.yeah.auto.repository.AutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class DefaultAutoService implements AutoService {

    public final AutoRepository autoRepository;
    private final AutoConverter autoConverter;
    private final AutoGroupMapper autoGroupMapper;
    private final AutoMapper autoMapper;


    @Override
    public AutoDto addAuto(AutoDto autoDto) throws ValidationException {
        validateAutoDto(autoDto);
        Auto addedAuto = autoRepository.save(autoMapper.mapFromDto(autoDto));
        return autoMapper.mapToDto(addedAuto);
    }

    private void validateAutoDto(AutoDto autoDto) throws ValidationException {
        if (isNull(autoDto)) {
            throw new ValidationException("Auto object is null");
        }
        if (isNull(autoDto.getPlate()) || autoDto.getPlate().isEmpty()){
            throw new ValidationException("Plate is empty");
        }
        if (isNull(autoDto.getMark()) || autoDto.getMark().isEmpty()){
            throw new ValidationException("Mark is empty");
        }
    }

    @Override
    public void deleteAuto(UUID id) {

        autoRepository.deleteById(id);
    }

    @Override
    public AutoDto findByPlate(String plate) {
        Auto auto = autoRepository.findByPlate(plate);
        if (auto != null) {
            return autoMapper.mapToDto(auto);
        }
        return null;
    }

    @Override
    public List<AutoDto> findAll() {
        return autoRepository.findAll()
                .stream()
                .map(autoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AutoDto> findAllByMark(String mark) {
        return autoRepository.findAllByMark(mark)
                .stream()
                .map(autoMapper::mapToDto)
                .collect(Collectors.toList());
    }

}
