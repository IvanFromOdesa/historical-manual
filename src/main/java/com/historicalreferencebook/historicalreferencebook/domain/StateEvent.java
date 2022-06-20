package com.historicalreferencebook.historicalreferencebook.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="state_event")
public class StateEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="state_e_key")
    private Integer statKey;

    @Column(name="event_gdp")
    private Integer eventGdp;

    @Column(name="event_population")
    private Integer populationEvent;

    @Column(name="on_date_event")
    private java.sql.Date onDateE;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "state_id", referencedColumnName = "id_state")
    private State state;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "event_id", referencedColumnName = "id_event")
    private Event event;

    public Integer getStatKey() {
        return statKey;
    }

    public void setStatKey(Integer statKey) {
        this.statKey = statKey;
    }

    public Integer getEventGdp() {
        return eventGdp;
    }

    public void setEventGdp(Integer eventGdp) {
        this.eventGdp = eventGdp;
    }

    public Integer getPopulationEvent() {
        return populationEvent;
    }

    public void setPopulationEvent(Integer populationEvent) {
        this.populationEvent = populationEvent;
    }

    public Date getOnDateE() {
        return onDateE;
    }

    public void setOnDateE(Date onDateE) {
        this.onDateE = onDateE;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
