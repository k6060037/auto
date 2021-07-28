package com.yeah.auto.controllers;

import com.yeah.auto.dto.AutoDto;
import com.yeah.auto.dto.AutoGroupDto;
import com.yeah.auto.exception.ValidationException;
import com.yeah.auto.projections.AutoGroupProjection;
import com.yeah.auto.repository.AutoGroupRepository;
import com.yeah.auto.repository.AutoRepository;
import com.yeah.auto.service.AutoGroupConverter;
import com.yeah.auto.service.AutoGroupService;
import com.yeah.auto.service.AutoService;
import com.yeah.auto.services.MarkInfo;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController("/")
@AllArgsConstructor
@Log
@CrossOrigin
public class AutoController {

    private final AutoService autoService;
    private final AutoRepository autoRepository;
    private final AutoGroupRepository autoGroupRepository;
    private final AutoGroupService autoGroupService;
    private final AutoGroupConverter autoGroupConverter;

    @GetMapping(value = "autos")
    public List<AutoDto> getAllAutos(){

        return autoService.findAll();
    }

    @GetMapping(value = "auto_by_plate")
    public AutoDto getAutoDtoByPlate(@RequestParam(name = "plate") String plate) {
        return autoService.findByPlate(plate);
    }

    @GetMapping(value = "autos/skoda")
    public MarkInfo skodaInfo() {
        MarkInfo skodaInfo = new MarkInfo();
        skodaInfo.setAutoDtos(autoService.findAllByMark("skoda"));
        skodaInfo.setAutosCount(String.valueOf(skodaInfo.getAutoDtos().size()));
        return skodaInfo;
    }

    @GetMapping(value = "autos/mercedes")
    public MarkInfo mercedesInfo() {
        MarkInfo mercedesInfo = new MarkInfo();
        mercedesInfo.setAutoDtos(autoService.findAllByMark("mercedes"));
        mercedesInfo.setAutosCount(String.valueOf(mercedesInfo.getAutoDtos().size()));
        return mercedesInfo;
    }

    @GetMapping(value = "autos/toyota")
    public MarkInfo toyotaInfo() {
        MarkInfo toyotaInfo = new MarkInfo();
        toyotaInfo.setAutoDtos(autoService.findAllByMark("toyota"));
        toyotaInfo.setAutosCount(String.valueOf(toyotaInfo.getAutoDtos().size()));
        return toyotaInfo;
    }
/*
    @GetMapping(value = "add_auto")
    public String addAuto(@RequestParam(name = "plate") String plate,
                        @RequestParam(name = "mark") String mark,
                        @RequestParam(name = "model", required = false) String model) {
        Auto auto = new Auto();
        auto.setModel(model);
        auto.setMark(mark);
        auto.setPlate(plate);
        autoRepository.save(auto);

        AutoGroup autoGroup = new AutoGroup();
        autoGroup.setMark(mark);
        autoGroupRepository.save(autoGroup);
        auto.setAutoGroup(autoGroup);
        autoRepository.save(auto);
        autoGroupRepository.save(autoGroup);


        return "Автомобиль с номером " + plate + " успешно добавлен в группу!";

    }
*/
    @GetMapping(value = "remove_auto")
    public String removeAutoByPlate(@RequestParam("plate") String plate) {

        UUID id = autoService.findByPlate(plate).getId();
        autoService.deleteAuto(id);
        return "Автомобиль с номером " + plate + " успешно удален!";

    }


    @GetMapping(value = "auto_group_by_mark")
    public AutoGroupProjection findAutoGroupByMark(@RequestParam(name = "mark") String mark) {
        AutoGroupProjection autoGroup = autoGroupRepository.findAutoGroupProjectionByMark(mark);
        return autoGroup;

    }

    @GetMapping(value = "auto_groups")
    public List<AutoGroupDto> getAllAutoGroups(){
        return autoGroupService.findAll();

    }

    @GetMapping(value = "add_auto")
    public AutoDto addAutoDto(@RequestParam(name = "plate") String plate,
                          @RequestParam(name = "mark") String mark,
                          @RequestParam(name = "model", required = false) String model) throws ValidationException {
        AutoDto autoDto = new AutoDto();
        autoDto.setModel(model);
        autoDto.setMark(mark);
        autoDto.setPlate(plate);

        List<String> autoGroupsMarks = new ArrayList<>();
        List<AutoGroupDto> autoGroupDtoList = autoGroupService.findAll();
        for (AutoGroupDto group:
                autoGroupDtoList) {
            String tempMark = group.getMark();
            autoGroupsMarks.add(tempMark);
        }

        if (!autoGroupsMarks.contains(autoDto.getMark())) {
            AutoGroupDto autoGroupDto = new AutoGroupDto();
            autoGroupDto.setMark(mark);

            autoGroupService.addAutoGroup(autoGroupDto);

            autoDto.setAutoGroupDto(autoGroupDto);

            autoService.addAuto(autoDto);
            return autoDto;
        } else {
            AutoGroupDto autoGroupDto = autoGroupService.findByMark(autoDto.getMark());
            autoDto.setAutoGroupDto(autoGroupDto);
            autoService.addAuto(autoDto);
            return autoDto;
        }
    }

    @GetMapping(value = "auto_groups_marks")
    public List<String> getMarks(){
        List<String> autoGroupsMarks = new ArrayList<>();
        List<AutoGroupDto> autoGroupDtoList = autoGroupService.findAll();
        for (AutoGroupDto group:
             autoGroupDtoList) {
            String mark = group.getMark();
            autoGroupsMarks.add(mark);
        }

        return autoGroupsMarks;
    }

}
