package com.example.hospitalreservation.service;

import com.example.hospitalreservation.common.ErrorMessage;
import com.example.hospitalreservation.common.SuccessMessage;
import com.example.hospitalreservation.dto.ReservationDTO;
import com.example.hospitalreservation.domain.Doctor;
import com.example.hospitalreservation.domain.Reservation;
import com.example.hospitalreservation.model.CreateReservationResponse;
import com.example.hospitalreservation.model.DeleteReservationResponse;
import com.example.hospitalreservation.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final Doctor doctor;
    private Long nextId = 1L;

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    public ReservationService(ReservationRepository reservationRepository, Doctor doctor) {
        this.reservationRepository = reservationRepository;
        this.doctor = doctor;
    }

    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(ReservationDTO::fromReservation)
                .collect(Collectors.toList());
    }

    public CreateReservationResponse createReservation(Long doctorId, Long patientId, LocalDateTime reservationTime) {
        try {
            doctor.isValid(reservationTime); // 의사 진료 시간 범위 체크
            isExist(reservationTime); // 예약 시간 중복 체크
            Reservation reservation = Reservation.of(nextId++, doctorId, patientId, reservationTime);
            Reservation savedReservation = reservationRepository.save(reservation);
            return CreateReservationResponse.from(savedReservation, SuccessMessage.CREATE_RESERVATION.getMessage());
        } catch (Exception e) {
            Reservation failedReservation = Reservation.of(null, doctorId, patientId, reservationTime);
            return CreateReservationResponse.from(failedReservation, ErrorMessage.CREATE_FAIL.getMessage());
        }
    }

    public DeleteReservationResponse cancelReservation(Long id, String cancelReason) {
        try {
            reservationRepository.deleteById(id);
            logger.info("예약 취소됨 - 예약 ID: {}, 취소 사유: {}", id, cancelReason);
            return DeleteReservationResponse.from(SuccessMessage.DELETE_RESERVATION.getMessage());
        } catch (Exception e) {
            logger.error("예약 취소 실패 - 예약 ID: {}, 오류 메시지: {}", id, e.getMessage());
            return DeleteReservationResponse.from(ErrorMessage.DELETE_FAIL.getMessage());
        }
    }

    /* 일단 킵
    public void cancelReservation(Long id) {
        reservationRepository.deleteById(id);
        return;
    }
     */

    private void isExist(LocalDateTime reservationTime) {
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation : reservations) {
            if (reservation.getReservationTime().isEqual(reservationTime)) {
                throw new IllegalArgumentException("해당 시간에는 이미 예약이 있습니다. 다른 시간을 선택해주세요.");
            }
        }
    }
}
