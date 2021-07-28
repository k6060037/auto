package com.yeah.auto.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yeah.auto.entity.AutoGroup;
import com.yeah.auto.service.AutoGroupConverter;
import lombok.*;


import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoDto {

    private UUID id;
    private String plate;
    private String mark;
    private String model;
    private AutoGroupDto autoGroupDto;


}

