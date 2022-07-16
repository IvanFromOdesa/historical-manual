package com.historicalreferencebook.historicalreferencebook.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="state_war")
@Getter
@Setter
@EqualsAndHashCode
public class StateWar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="war_state_id")
    private Integer warSKey;

    @Column(name="territory_war")
    private Integer territoryWar;

    @Column(name="population_war")
    private Integer populationWar;

    @Column(name="gdp_war")
    private Integer gdpWar;

    @Column(name="on_date_war")
    private java.sql.Date onDateWar;

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id_state")
    private State stateW;

    @ManyToOne
    @JoinColumn(name = "war_id", referencedColumnName = "id_war")
    private War war;
}
