package com.historicalreferencebook.historicalreferencebook.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "state")
public class State implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_state")
    private Integer idState;

    @Column(name = "official_state_name")
    private String officialStateName;

    @Column(name = "official_language")
    private String officialLanguage;

    @Column(name = "state_currency")
    private String stateCurrency;

    @OneToMany(mappedBy = "stateC", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StateCapital> stateCapitals;

    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StateEvent> stateEvents;

    @OneToMany(mappedBy = "stateW", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StateWar> stateWars;

    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Governor> governors;

    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Figure> figures;

    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Statistics> statistics;

    public State() {
    }

    public State(Integer idState, String officialStateName) {
        this.idState = idState;
        this.officialStateName = officialStateName;
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public void setFigures(List<Figure> figures) {
        this.figures = figures;
    }

    public List<Statistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<Statistics> statistics) {
        this.statistics = statistics;
    }

    public List<Governor> getGovernors() {return governors;}

    public void setGovernors(List<Governor> governors) {this.governors = governors;}

    public List<StateWar> getStateWars() {return stateWars;}

    public void setStateWars(List<StateWar> stateWars) {this.stateWars = stateWars;}

    public List<StateEvent> getStateEvents() {return stateEvents;}

    public void setStateEvents(List<StateEvent> stateEvents) {this.stateEvents = stateEvents;}

    public List<StateCapital> getStateCapitals() {return stateCapitals;}

    public void setStateCapitals(List<StateCapital> stateCapitals) {this.stateCapitals = stateCapitals;}

    public Integer getIdState() {return this.idState;}

    public void setIdState(Integer idState) {this.idState = idState;}

    public String getOfficialStateName() {
        return this.officialStateName;
    }

    public void setOfficialStateName(String officialStateName) {
        this.officialStateName = officialStateName;
    }

    public String getOfficialLanguage() {
        return this.officialLanguage;
    }

    public void setOfficialLanguage(String officialLanguage) {
        this.officialLanguage = officialLanguage;
    }

    public String getStateCurrency() {
        return this.stateCurrency;
    }

    public void setStateCurrency(String stateCurrency) {
        this.stateCurrency = stateCurrency;
    }
}
