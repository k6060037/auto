package com.yeah.auto.services;

import com.yeah.auto.dto.AutoDto;
import com.yeah.auto.projections.AutoProjection;
import lombok.Data;

import java.util.List;

@Data
public class MarkInfo {
    private List<AutoDto> autoDtos;
    private String autosCount;


}
