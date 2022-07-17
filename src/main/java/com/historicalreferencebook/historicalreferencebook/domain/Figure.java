package com.historicalreferencebook.historicalreferencebook.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "figure")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
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
}
