package com.historicalreferencebook.historicalreferencebook.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="capital")
@Getter
@Setter
@EqualsAndHashCode(exclude = "stateCapitals")
@RequiredArgsConstructor
@NoArgsConstructor
public class Capital implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_capital")
    @NonNull
    private Integer idCapital;

    @Column(name="official_capital_name", nullable = false, unique = true)
    @NonNull
    private String officialCapitalName;

    @Column(name="capital_population", nullable = false)
    @NonNull
    private Integer capitalPopulation;

    @Column(name="capital_square", nullable = false)
    @NonNull
    private Integer capitalSquare;

    @OneToMany(mappedBy = "capital", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<StateCapital> stateCapitals;
}
