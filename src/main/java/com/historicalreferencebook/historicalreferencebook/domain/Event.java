package com.historicalreferencebook.historicalreferencebook.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "event")
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    private Integer idEvent;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "date_of_beginning")
    private java.sql.Date dateOfBeginning;

    @Column(name = "date_of_end")
    private java.sql.Date dateOfEnd;

    @Column(name = "event_subject")
    private String eventSubject;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StateEvent> stateEvents;

    public List<StateEvent> getStateEvents() {return stateEvents;}

    public void setStateEvents(List<StateEvent> stateEvents) {this.stateEvents = stateEvents;}

    public Integer getIdEvent() {
        return this.idEvent;
    }

    public void setIdEvent(Integer idEvent) {this.idEvent = idEvent;}

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    public String getEventSubject() {
        return this.eventSubject;
    }

    public void setEventSubject(String eventSubject) {
        this.eventSubject = eventSubject;
    }
}
