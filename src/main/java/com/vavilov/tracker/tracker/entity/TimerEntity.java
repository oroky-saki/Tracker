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

    public TimerEntity(String title, Date start_time, int value, int value_pause, String status, GroupEntity group) {
        this.title = title;
        this.start_time = start_time;
        this.value = value;
        this.value_pause = value_pause;
        this.status = status;
        this.group = group;
    }

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;

}
