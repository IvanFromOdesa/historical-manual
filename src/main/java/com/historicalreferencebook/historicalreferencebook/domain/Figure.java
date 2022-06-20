package com.historicalreferencebook.historicalreferencebook.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "figure")
public class Figure implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_figure")
    private Integer idFigure;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "date_of_birth")
    private java.sql.Date dateOfBirth;

    @Column(name = "date_of_death", nullable = true)
    private java.sql.Date dateOfDeath;

    @Column(name = "kind_of_activity")
    private String kindOfActivity;

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id_state")
    private State state;

    public Integer getIdFigure() {
        return idFigure;
    }

    public void setIdFigure(Integer idFigure) {
        this.idFigure = idFigure;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getKindOfActivity() {
        return kindOfActivity;
    }

    public void setKindOfActivity(String kindOfActivity) {
        this.kindOfActivity = kindOfActivity;
    }

    public State getState() {return state;}

    public void setState(State state) {
        this.state = state;
    }
}
