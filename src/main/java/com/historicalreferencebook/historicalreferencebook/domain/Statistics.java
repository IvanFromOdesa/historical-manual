package com.historicalreferencebook.historicalreferencebook.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "statistics")
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

    public State getState() {return state;}

    public void setState(State state) {this.state = state;}

    public Integer getIdStats() {
        return this.idStats;
    }

    public void setIdStats(Integer idStats) {
        this.idStats = idStats;
    }

    public Integer getGeneralPopulation() {
        return this.generalPopulation;
    }

    public void setGeneralPopulation(Integer generalPopulation) {
        this.generalPopulation = generalPopulation;
    }

    public Double getGeneralGdp() {
        return this.generalGdp;
    }

    public void setGeneralGdp(Double generalGdp) {
        this.generalGdp = generalGdp;
    }

    public Integer getGeneralTerritory() {
        return this.generalTerritory;
    }

    public void setGeneralTerritory(Integer generalTerritory) {
        this.generalTerritory = generalTerritory;
    }

    public java.sql.Date getOnDate() {
        return this.onDate;
    }

    public void setOnDate(java.sql.Date onDate) {
        this.onDate = onDate;
    }
}
