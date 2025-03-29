package com.example.hospitalreservation.service;

import com.example.hospitalreservation.model.CreateReservationRequest;
import com.example.hospitalreservation.model.DeleteReservationRequest;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.repository.ReservationRepository;
import com.example.hospitalreservation.utils.GlobalLogger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {
    private static final int START_TIME_AVAILABLE = 9;
    private static final int END_TIME_AVAILABLE = 17;

    private final ReservationRepository reservationRepository;
    private final TimeTable timeTable;

    public ReservationService(ReservationRepository reservationRepository, TimeTable timeTable) {
        this.reservationRepository = reservationRepository;
        this.timeTable = timeTable;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservation(CreateReservationRequest dto) {
        Reservation reservation = Reservation.from(dto);

        checkAvailableTimes(reservation);
        timeTable.enroll(reservation);

        return reservationRepository.save(reservation);
    }

    public void cancelReservation(Long id, DeleteReservationRequest dto) {
        reservationRepository.deleteById(id);
        timeTable.cancelById(id);

        GlobalLogger.log(dto.getCancelReason());
    }

    private void checkAvailableTimes(Reservation reservation) {
        LocalDateTime today = LocalDate.now().atStartOfDay();
        LocalDateTime availableStartTime = today.withHour(START_TIME_AVAILABLE);
        LocalDateTime availableEndTime = today.withHour(END_TIME_AVAILABLE);

        if (!reservation.isFullyContainedIn(availableStartTime, availableEndTime)) {
            throw new IllegalArgumentException("의사의 진료 가능시간 (%02d:%02d~%02d:%02d) 내에서만 예약할 수 있습니다."
                    .formatted(availableStartTime.getHour(), availableStartTime.getMinute()
                            , availableEndTime.getHour(), availableEndTime.getMinute()));
        }
    }
}
