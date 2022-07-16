package com.historicalreferencebook.historicalreferencebook.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "statistics")
@Getter
@Setter
@EqualsAndHashCode
public class Statistics implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stats")
    private Integer idStats;

    @Column(name = "general_population")
    private Integer generalPopulation;

    @Column(name = "general_gdp")
    private Double generalGdp;

    @Column(name = "general_territory")
    private Integer generalTerritory;

    @Column(name = "on_date")
    private java.sql.Date onDate;

    @ManyToOne
    @JoinColumn(name = "id_state", nullable = false)
    private State state;
}
