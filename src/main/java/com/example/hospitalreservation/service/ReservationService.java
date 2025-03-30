package com.example.hospitalreservation.service;

import com.example.hospitalreservation.dto.ReservationDTO;
import com.example.hospitalreservation.model.Reservation;
import org.springframework.stereotype.Service;

import com.example.hospitalreservation.repository.ReservationRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// TODO : 서비스 레이어에서 필요한 어노테이션을 작성해주세요.
@Service
public class ReservationService {

    // TODO : 주입 받아야 할 객체를 작성해주세요.
	private ReservationRepository reservationRepository;

	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}


    // TODO : 모든 예약 리스트를 조회하는 코드를 작성해주세요.
    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(ReservationDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // TODO : 새로운 예약을 생성하는 코드를 작성해주세요.
    public Reservation createReservation(Long doctorId, Long patientId, LocalDateTime reservationTime) {
        isValidReservationTime(reservationTime);

        isDuplicateTime(reservationTime);

        Reservation reservation = Reservation.of(doctorId, patientId, reservationTime);
        return reservationRepository.save(reservation);
    }

    // TODO : 예약을 취소하는 코드를 작성해주세요.
    public void cancelReservation(Long id) {
        reservationRepository.deleteById(id);
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

}
