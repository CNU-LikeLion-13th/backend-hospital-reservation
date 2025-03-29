package com.example.hospitalreservation.service;

import com.example.hospitalreservation.utils.GlobalLogger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TimeTable {

    private Map<Number, TimeEntity> timetable;

    public TimeTable() {
        this.timetable = new HashMap<>();
    }

    public void enroll(TimeEntity timeEntity) {
        // 예약을 타임테이블에 등록하기
        checkConflict(timeEntity);
        timetable.put(timeEntity.getId(), timeEntity);
    }

    public void cancelById(Number id) {
        // 예약을 타임테이블에서 제거
        timetable.remove(id);
    }

    private void checkConflict(TimeEntity timeEntity) {
        // 예약이 현재 타임테이블에서 겹치는지 확인하기
        GlobalLogger.log("given " + timeEntity.getStartTime() + " and " + timeEntity.getEndTime());

        timetable.forEach((id, otherEntity) -> {
            GlobalLogger.log("Checking conflict for " + otherEntity.getStartTime() + " and " + otherEntity.getEndTime());
            if (otherEntity.conflictsWith(timeEntity)) {
                throw new IllegalArgumentException("해당 시간에는 이미 예약이 있습니다. 다른 시간을 선택해주세요.");
            }
        });
    }
}
