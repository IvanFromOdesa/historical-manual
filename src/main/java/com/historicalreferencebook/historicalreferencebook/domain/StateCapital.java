package com.historicalreferencebook.historicalreferencebook.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="state_capital")
@Getter
@Setter
@EqualsAndHashCode
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
}
