package com.yeah.auto.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "auto_groups")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class AutoGroup {
    @Id
    @Column(name = "auto_group_id")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @EqualsAndHashCode.Include
    UUID id;
    @Column(name = "mark")
    String mark;
    @JoinColumn(name = "auto_group_id")
    @OneToMany(fetch = FetchType.LAZY)
//    @JsonBackReference
    List<Auto> autos = new ArrayList<>();

    public void addAuto(Auto auto) {
        if (auto.getId() == null || !autos.contains(auto)){
            auto.setAutoGroup(this);
            autos.add(auto);
        }
    }

    public List<Auto> getAutos() {
        return Collections.unmodifiableList(autos);
    }


}
