package com.historicalreferencebook.historicalreferencebook.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "governor")
@Getter
@Setter
@EqualsAndHashCode
public class Governor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_governor")
    private Integer idGovernor;

    @Column(name = "pib")
    private String fullName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "position")
    private String position;

    @Column(name = "date_of_intercession")
    private java.sql.Date dateOfIntercession;

    @Column(name = "date_of_resignation")
    private java.sql.Date dateOfResignation;

    @Column(name = "per_capita_income")
    private Double perCapitaIncome;

    @Column(name = "population_below_poverty")
    private Integer populationBelowPoverty;

    @ManyToOne
    @JoinColumn(name = "id_state", nullable = false)
    private State state;
}
