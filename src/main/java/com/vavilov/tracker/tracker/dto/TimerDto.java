package com.vavilov.tracker.tracker.dto;

import com.vavilov.tracker.tracker.entity.GroupEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
public class TimerDto {

    private Long id;

    private String title;

    private Date start_time;

    private int value;

    private int value_pause;

    private String status;

}
