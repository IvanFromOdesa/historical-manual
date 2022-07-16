package com.historicalreferencebook.historicalreferencebook.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="capital")
@Getter
@Setter
@EqualsAndHashCode(exclude = "stateCapitals")
public class Capital implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_capital")
    private Integer idCapital;

    @Column(name="official_capital_name", nullable = false, unique = true)
    private String officialCapitalName;

    @Column(name="capital_population", nullable = false)
    private Integer capitalPopulation;

    @Column(name="capital_square", nullable = false)
    private Integer capitalSquare;

    @OneToMany(mappedBy = "capital", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<StateCapital> stateCapitals;
}
