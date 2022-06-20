package com.historicalreferencebook.historicalreferencebook.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="state_capital")
public class StateCapital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "capital_key")
    private  Integer capKey;

    @Column(name="date_of_formation")
    private java.sql.Date dateOfFormation;

    @Column(name="termination_date")
    private java.sql.Date terminationDate;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "state_id", referencedColumnName = "id_state")
    private State stateC;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "capital_id", referencedColumnName = "id_capital")
    private Capital capital;

    public State getStateC() {return stateC;}

    public void setStateC(State stateC) {this.stateC = stateC;}

    public Integer getCapKey() {
        return capKey;
    }

    public void setCapKey(Integer capKey) {
        this.capKey = capKey;
    }

    public Date getDateOfFormation() {
        return dateOfFormation;
    }

    public void setDateOfFormation(Date dateOfFormation) {
        this.dateOfFormation = dateOfFormation;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public State getState() {
        return stateC;
    }

    public void setState(State stateC) {
        this.stateC = stateC;
    }

    public Capital getCapital() {
        return capital;
    }

    public void setCapital(Capital capital) {
        this.capital = capital;
    }
}
