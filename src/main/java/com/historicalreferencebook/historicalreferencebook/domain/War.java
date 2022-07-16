package com.historicalreferencebook.historicalreferencebook.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "war")
@Getter
@Setter
@EqualsAndHashCode(exclude = "stateWars")
public class War implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_war")
    private Integer idWar;

    @Column(name = "war_name")
    private String warName;

    @Column(name = "participating_states")
    private String participatingStates;

    @Column(name = "date_of_beginning")
    private java.sql.Date dateOfBeginning;

    @Column(name = "date_of_end")
    private java.sql.Date dateOfEnd;

    @Column(name = "victor")
    private String victor;

    @Column(name = "results")
    private String results;

    @OneToMany(mappedBy = "war", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<StateWar> stateWars;
}
