package com.historicalreferencebook.historicalreferencebook.domain;

import lombok.*;
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
@Getter
@Setter
@EqualsAndHashCode(exclude = "stateEvents")
@RequiredArgsConstructor
@NoArgsConstructor
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    @NonNull
    private Integer idEvent;

    @Column(name = "event_name")
    @NonNull
    private String eventName;

    @Column(name = "date_of_beginning")
    @NonNull
    private java.sql.Date dateOfBeginning;

    @Column(name = "date_of_end")
    @NonNull
    private java.sql.Date dateOfEnd;

    @Column(name = "event_subject")
    @NonNull
    private String eventSubject;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<StateEvent> stateEvents;
}
