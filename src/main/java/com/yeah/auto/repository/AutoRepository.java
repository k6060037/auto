package com.yeah.auto.repository;

import com.yeah.auto.entity.Auto;
import com.yeah.auto.projections.AutoProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AutoRepository extends JpaRepository<Auto, UUID> {

    AutoProjection findProjectionById(UUID id);
    AutoProjection findProjectionByPlate(String plate);
    List<Auto> findAllByMark(String mark);
    Auto findByPlate(String plate);

}
