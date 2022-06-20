package com.historicalreferencebook.historicalreferencebook.domain;

import javax.persistence.*;

@Entity
@Table(name="state_war")
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

    public Integer getWarSKey() {
        return warSKey;
    }

    public void setWarSKey(Integer warSKey) {
        this.warSKey = warSKey;
    }

    public Integer getTerritoryWar() {
        return territoryWar;
    }

    public void setTerritoryWar(Integer territoryWar) {
        this.territoryWar = territoryWar;
    }

    public Integer getPopulationWar() {
        return populationWar;
    }

    public void setPopulationWar(Integer populationWar) {
        this.populationWar = populationWar;
    }

    public Integer getGdpWar() {
        return gdpWar;
    }

    public void setGdpWar(Integer gdpWar) {
        this.gdpWar = gdpWar;
    }

    public java.sql.Date getOnDateWar() {
        return onDateWar;
    }

    public void setOnDateWar(java.sql.Date onDateWar) {
        this.onDateWar = onDateWar;
    }

    public State getStateW() {
        return stateW;
    }

    public void setStateW(State stateW) {
        this.stateW = stateW;
    }

    public War getWar() {
        return war;
    }

    public void setWar(War war) {
        this.war = war;
    }
}
