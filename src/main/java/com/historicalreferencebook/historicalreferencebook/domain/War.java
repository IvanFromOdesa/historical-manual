package com.historicalreferencebook.historicalreferencebook.domain;


import lombok.*;

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
@RequiredArgsConstructor
@NoArgsConstructor
public class War implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_war")
    @NonNull
    private Integer idWar;

    @Column(name = "war_name")
    @NonNull
    private String warName;

    @Column(name = "participating_states")
    @NonNull
    private String participatingStates;

    @Column(name = "date_of_beginning")
    @NonNull
    private java.sql.Date dateOfBeginning;

    @Column(name = "date_of_end")
    @NonNull
    private java.sql.Date dateOfEnd;

    @Column(name = "victor")
    @NonNull
    private String victor;

    @Column(name = "results")
    @NonNull
    private String results;

    @OneToMany(mappedBy = "war", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<StateWar> stateWars;
}
