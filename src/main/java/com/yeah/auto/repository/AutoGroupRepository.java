package com.yeah.auto.repository;

import com.yeah.auto.entity.AutoGroup;
import com.yeah.auto.projections.AutoGroupProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface AutoGroupRepository extends JpaRepository<AutoGroup, UUID> {
    AutoGroup findAutoGroupByMark(String mark);
    AutoGroupProjection findAutoGroupProjectionByMark(String mark);
    List<AutoGroupProjection> findBy();
}
