package com.example.hospitalreservation.service;

import com.example.hospitalreservation.dto.*;
import com.example.hospitalreservation.enums.ReservationType;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.repository.TimeTable;
import com.example.hospitalreservation.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TimeTable timeTable;

    public ReservationService(ReservationRepository reservationRepository, TimeTable timeTable) {
        this.reservationRepository = reservationRepository;
        this.timeTable = timeTable;
    }

    public List<ShowReservationResponse> getAllReservations() {
        log.info("Get all reservations Service");
        return reservationRepository.findAll().stream()
                .map(reservation-> ShowReservationResponse.from(reservation)).toList();
    }

    public CreateReservationResponse createReservation(CreateReservationRequest dto) throws Exception {

        Reservation reservation = new Reservation(
                dto.getPatientId(),
                dto.getDoctorId(),
                dto.getReservationStartTime(),
                dto.getReservationEndTime(),
                dto.getReason());

        reservation.setFee(ReservationType.calculateFee(dto.getReason()));
        reservation.setId(reservationRepository.getNextId());
        try {
            timeTable.enroll(reservation);
            reservationRepository.save(reservation);

            return CreateReservationResponse.success(reservation.getId(), "예약이 완료되었습니다.", reservation.getFee());
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public DeleteReservationResponse cancelReservation(DeleteReservationRequest dto, Long id) throws Exception {
        try {
            reservationRepository.deleteById(id);
            timeTable.remove(id);

            log.info("예약 취소 : {}", dto.getCancelReason());
            return DeleteReservationResponse.success("예약이 취소되었습니다.");
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }
}
