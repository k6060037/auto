package com.yeah.auto.dto;

import com.yeah.auto.entity.Auto;
import lombok.*;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoGroupDto {
    UUID id;
    String mark;
}
