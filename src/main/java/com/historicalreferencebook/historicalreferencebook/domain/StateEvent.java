package com.historicalreferencebook.historicalreferencebook.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="state_event")
@Getter
@Setter
@EqualsAndHashCode
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
}
