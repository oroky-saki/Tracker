package com.vavilov.tracker.tracker.utils;

import com.vavilov.tracker.tracker.entity.TimerEntity;
import com.vavilov.tracker.tracker.repository.TimerRepo;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TimerUtil {

    public static TimerEntity runTimer(TimerEntity timerEntity) {

        // Получение времени старта в секундах
        Long st = timerEntity.getStart_time().getTime();
        int startTime = st.intValue();

        // Создание новой сущности для замены старой
        TimerEntity newTimer = new TimerEntity();

        newTimer.setStatus("run");
        newTimer.setStart_time(timerEntity.getStart_time());
        newTimer.setTitle(timerEntity.getTitle());
        newTimer.setId(timerEntity.getId());
        newTimer.setGroup(timerEntity.getGroup());

        // Получение текущего времени в секундах и перевод к типу long
        Long date = new Date().getTime();
        int currentTime = date.intValue();

        newTimer.setValue_pause(currentTime - startTime - timerEntity.getValue());
        newTimer.setValue(currentTime - startTime - newTimer.getValue_pause());

        return newTimer;
    }

    public static TimerEntity pauseTimer(TimerEntity timerEntity) {

        // Получение времени старта в секундах
        Long st = timerEntity.getStart_time().getTime();
        int startTime = st.intValue();

        TimerEntity newTimer = new TimerEntity();
        newTimer.setStart_time(timerEntity.getStart_time());
        newTimer.setTitle(timerEntity.getTitle());
        newTimer.setId(timerEntity.getId());
        newTimer.setGroup(timerEntity.getGroup());

        // Смена статуса на "паза"
        newTimer.setStatus("pause");

        // Получение текущего времени в секундах и перевод к типу long
        Long date = new Date().getTime();
        int currentTime = date.intValue();

        newTimer.setValue(currentTime - startTime - timerEntity.getValue_pause());
        newTimer.setValue_pause(currentTime - startTime - newTimer.getValue());

        return newTimer;
    }

    public static TimerEntity stopTimer(TimerEntity timerEntity, String prevStatus) {

        // Получение времени старта в секундах
        Long st = timerEntity.getStart_time().getTime();
        int startTime = st.intValue();

        TimerEntity newTimer = new TimerEntity();
        newTimer.setStart_time(timerEntity.getStart_time());
        newTimer.setTitle(timerEntity.getTitle());
        newTimer.setId(timerEntity.getId());
        newTimer.setGroup(timerEntity.getGroup());

        // Смена статуса на "паза"
        newTimer.setStatus("stop");

        // Получение текущего времени в секундах и перевод к тиму long
        Long date = new Date().getTime();
        int currentTime = date.intValue();

        if (prevStatus.equals("run")) {
            newTimer.setValue_pause(currentTime - startTime - timerEntity.getValue());
            newTimer.setValue(currentTime - startTime - newTimer.getValue_pause());
        } else {
            newTimer.setValue(currentTime - startTime - timerEntity.getValue_pause());
            newTimer.setValue_pause(currentTime - startTime - newTimer.getValue());
        }

        return newTimer;

    }
}
