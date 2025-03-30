package com.example.hospitalreservation.service;

import com.example.hospitalreservation.dto.ReservationDTO;
import com.example.hospitalreservation.model.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.hospitalreservation.repository.ReservationRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

	private ReservationRepository reservationRepository;

    private static final Logger log = LoggerFactory.getLogger(ReservationService.class);

	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(ReservationDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Reservation createReservation(Long doctorId, Long patientId, LocalDateTime reservationTime) {
        isValidReservationTime(reservationTime);

        isDuplicateTime(reservationTime);

        Reservation reservation = Reservation.of(doctorId, patientId, reservationTime);
        return reservationRepository.save(reservation);
    }

    public void isValidReservationTime(LocalDateTime time) {
        int startHour = time.getHour();
        int minute = time.getMinute();

        if (startHour < 9 || startHour >= 17 ) {
            throw new IllegalArgumentException("의사의 진료 가능 시간(09:00 ~ 17:00) 내에서만 예약할 수 있습니다.");
        } else if (minute != 0) {
            throw new IllegalArgumentException("예약 시간은 1시간 단위로 예약 가능합니다.");
        }
    }

    public void isDuplicateTime(LocalDateTime startHour) {
        boolean isDuplicate =  reservationRepository.findAll().stream()
                .anyMatch(reservation ->
                    reservation.getReservationTime().equals(startHour)
                );
        if(isDuplicate) {
            throw new IllegalArgumentException("해당 시간에는 이미 예약이 있습니다. 다른 시간을 선택해주세요.");
        }
    }

    public void cancelReservation(Long id, String cancelReason) {
        if (isReservationExists(id)) {
            log.info("예약 ID {} 취소, 사유 : {}", id , cancelReason);
            reservationRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("존재하지 않는 예약입니다.");
        }
    }

    public boolean isReservationExists(Long id) {
        return reservationRepository.findAll().stream()
                .anyMatch(reservation ->
                        reservation.getId().equals(id)
                );
    }
}
