package com.historicalreferencebook.historicalreferencebook.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "governor")
public class Governor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_governor")
    private Integer idGovernor;

    @Column(name = "pib")
    private String fullName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "position")
    private String position;

    @Column(name = "date_of_intercession")
    private java.sql.Date dateOfIntercession;

    @Column(name = "date_of_resignation")
    private java.sql.Date dateOfResignation;

    @Column(name = "per_capita_income")
    private Double perCapitaIncome;

    @Column(name = "population_below_poverty")
    private Integer populationBelowPoverty;

    @ManyToOne
    @JoinColumn(name = "id_state", nullable = false)
    private State state;

    public State getState() {return state;}

    public void setState(State state) {this.state = state;}

    public Integer getIdGovernor() {
        return this.idGovernor;
    }

    public void setIdGovernor(Integer idGovernor) {
        this.idGovernor = idGovernor;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {this.fullName = fullName;}

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public java.sql.Date getDateOfIntercession() {
        return this.dateOfIntercession;
    }

    public void setDateOfIntercession(java.sql.Date dateOfIntercession) {
        this.dateOfIntercession = dateOfIntercession;
    }

    public java.sql.Date getDateOfResignation() {
        return this.dateOfResignation;
    }

    public void setDateOfResignation(java.sql.Date dateOfResignation) {
        this.dateOfResignation = dateOfResignation;
    }

    public Double getPerCapitaIncome() {
        return this.perCapitaIncome;
    }

    public void setPerCapitaIncome(Double perCapitaIncome) {
        this.perCapitaIncome = perCapitaIncome;
    }

    public Integer getPopulationBelowPoverty() {
        return this.populationBelowPoverty;
    }

    public void setPopulationBelowPoverty(Integer populationBelowPoverty) {
        this.populationBelowPoverty = populationBelowPoverty;
    }
}
