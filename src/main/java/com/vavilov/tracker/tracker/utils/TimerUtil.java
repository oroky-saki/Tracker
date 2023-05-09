package com.vavilov.tracker.tracker.utils;

import com.vavilov.tracker.tracker.entity.TimerEntity;
import com.vavilov.tracker.tracker.repository.TimerRepo;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
public class TimerUtil {

    public static TimerEntity runTimer(TimerEntity timerEntity) {

        // Создание новой сущности для замены старой
        TimerEntity newTimer = new TimerEntity();

        newTimer.setStatus("run");
        newTimer.setTitle(timerEntity.getTitle());
        newTimer.setId(timerEntity.getId());
        newTimer.setGroup(timerEntity.getGroup());

        // Задание времени старта при первом старте
        if (timerEntity.getStatus().equals("default")) {
            newTimer.setStart_time(new Date());
        } else {
            newTimer.setStart_time(timerEntity.getStart_time());
        }

        // Получение времени старта в секундах
        Long st = newTimer.getStart_time().getTime();
        int startTime = st.intValue();

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
            newTimer.setValue_pause(timerEntity.getValue_pause());
            newTimer.setValue(currentTime - startTime - timerEntity.getValue_pause());
        } else {
            newTimer.setValue(timerEntity.getValue());
            newTimer.setValue_pause(currentTime - startTime - newTimer.getValue());
        }

        return newTimer;

    }

    public static String groupReport(List<TimerEntity> groupList) throws IOException {

        String timerLine = "";

        for (int i = 0; i < groupList.size(); i++) {

            TimerEntity timer = groupList.get(i);
            timerLine += timer.getTitle() + ":     " + (timer.getValue()/1000) + " seconds \n";
        }

        try {
            FileOutputStream fos = new FileOutputStream("Z:\\report.txt");
            fos.write(timerLine.getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            return e.getMessage();
        }

        return "Done";
    }
}
