package com.historicalreferencebook.historicalreferencebook.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "war")
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
    private List<StateWar> stateWars;

    public List<StateWar> getStateWars() {return stateWars;}

    public void setStateWars(List<StateWar> stateWars) {this.stateWars = stateWars;}

    public Integer getIdWar() {return this.idWar;}

    public void setIdWar(Integer idWar) {
        this.idWar = idWar;
    }

    public String getWarName() {
        return this.warName;
    }

    public void setWarName(String warName) {
        this.warName = warName;
    }

    public String getParticipatingStates() {
        return this.participatingStates;
    }

    public void setParticipatingStates(String participatingStates) {
        this.participatingStates = participatingStates;
    }

    public java.sql.Date getDateOfBeginning() {
        return this.dateOfBeginning;
    }

    public void setDateOfBeginning(java.sql.Date dateOfBeginning) {
        this.dateOfBeginning = dateOfBeginning;
    }

    public java.sql.Date getDateOfEnd() {
        return this.dateOfEnd;
    }

    public void setDateOfEnd(java.sql.Date dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

    public String getVictor() {
        return this.victor;
    }

    public void setVictor(String victor) {
        this.victor = victor;
    }

    public String getResults() {
        return this.results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}
