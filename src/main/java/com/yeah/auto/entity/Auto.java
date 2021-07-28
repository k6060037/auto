package com.yeah.auto.entity;

import com.fasterxml.jackson.annotation.*;
import liquibase.pro.packaged.A;
import liquibase.pro.packaged.C;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "autos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Auto {
    @Id
    @Column(name = "auto_id")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @EqualsAndHashCode.Include
    private UUID id;
    @Column(name = "plate")
    private String plate;
    @Column(name = "mark")
    private String mark;
    @Column(name = "model")
    private String model;
    @JoinColumn(name = "auto_group_id")
    @ManyToOne(fetch = FetchType.LAZY)
    //  @JsonManagedReference
//    @JsonIgnore
    private AutoGroup autoGroup;

}
