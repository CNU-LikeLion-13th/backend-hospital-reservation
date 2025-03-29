package com.example.hospitalreservation.service;

import com.example.hospitalreservation.common.SuccessMessage;
import com.example.hospitalreservation.domain.Reservation;
import com.example.hospitalreservation.model.*;
import com.example.hospitalreservation.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
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

    public CreateReservationResponse createReservation(CreateReservationRequest dto) {
        Reservation reservation = Reservation.from(dto);

        saveReservation(reservation);

        return CreateReservationResponse.from(reservation, SuccessMessage.CREATE_RESERVATION);
    }

    public DeleteReservationResponse cancelReservation(Long id, DeleteReservationRequest dto) {
        deleteReservation(id);

        log.info("{}", dto.getCancelReason());

        return DeleteReservationResponse.from(SuccessMessage.DELETE_RESERVATION);
    }

    private void saveReservation(Reservation reservation) {
        validate(reservation);
        insertReservationTime(reservation);

        reservationRepository.save(reservation);
    }

    private void insertReservationTime(Reservation reservation) {
        timeTable.enroll(reservation);
    }

    private void validate(Reservation reservation) {
        checkAvailableTimes(reservation);
    }

    private void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
        timeTable.cancelById(id);
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
