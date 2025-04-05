package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.Doctor;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.enums.ReservationException;
import com.example.hospitalreservation.model.ReservationTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class TimeTable {
    private final List<Map<Long, ReservationTime>> reservationTimeTable = new ArrayList<>();

    private boolean checkInTime(ReservationTime reservationTime) {
        return reservationTime.getStartTime().getHour() >= Doctor.getStartHour()
                && reservationTime.getEndTime().getHour() < Doctor.getEndHour();
    }

    private boolean checkConflict(ReservationTime reservationTime) {
        for (Map<Long, ReservationTime> map : reservationTimeTable) {
            for (Map.Entry<Long, ReservationTime> entry : map.entrySet()) {
                ReservationTime other = entry.getValue();
                if(reservationTime.isConflict(other)) return true;
            }
        }
        return false;
    }

    public void enroll(Reservation reservation) throws Exception {
        Long id = reservation.getId();
        ReservationTime reservationTime = new ReservationTime(reservation);
        if(!checkInTime(reservationTime)) throw new Exception(ReservationException.NOT_AVAILABLE_TIME.getErrorMessage());
        if(checkConflict(reservationTime)) throw new Exception(ReservationException.RESERVATION_TIME_ALREADY_EXISTS.getErrorMessage());

        Map<Long, ReservationTime> map = new HashMap<>();
        map.put(id, reservationTime);
        log.info("Enrolling reservation with id " + id + " and time " + reservationTime);

        reservationTimeTable.add(map);
    }

    public void remove(Long id) {
        reservationTimeTable.removeIf(map -> map.containsKey(id));
    }
}
