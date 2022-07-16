package com.historicalreferencebook.historicalreferencebook.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "state")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"stateCapitals", "stateEvents", "stateWars", "governors", "figures", "statistics"})
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
    private Set<StateCapital> stateCapitals;

    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<StateEvent> stateEvents;

    @OneToMany(mappedBy = "stateW", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<StateWar> stateWars;

    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Governor> governors;

    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Figure> figures;

    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Statistics> statistics;

    public State(Integer idState, String officialStateName) {
        this.idState = idState;
        this.officialStateName = officialStateName;
    }
}
