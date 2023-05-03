package com.vavilov.tracker.tracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TimerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Date start_time;

    private int value;

    private int value_pause;

    private String status;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;

}
