package com.historicalreferencebook.historicalreferencebook.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="capital")
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
    private List<StateCapital> stateCapitals;

    public List<StateCapital> getStateCapitals() {return stateCapitals;}

    public void setStateCapitals(List<StateCapital> stateCapitals) {this.stateCapitals = stateCapitals;}

    public Integer getIdCapital() {return idCapital;}

    public void setIdCapital(Integer idCapital) {this.idCapital = idCapital;}

    public String getOfficialCapitalName() {return officialCapitalName;}

    public void setOfficialCapitalName(String officialCapitalName) {this.officialCapitalName = officialCapitalName;}

    public Integer getCapitalPopulation() {return capitalPopulation;}

    public void setCapitalPopulation(Integer capitalPopulation) {this.capitalPopulation = capitalPopulation;}

    public Integer getCapitalSquare() {return capitalSquare;}

    public void setCapitalSquare(Integer capitalSquare) {this.capitalSquare = capitalSquare;}
}
