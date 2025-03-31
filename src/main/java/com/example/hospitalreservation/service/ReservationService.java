package com.example.hospitalreservation.service;

import com.example.hospitalreservation.dto.ReservationDto;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

// TODO : 서비스 레이어에서 필요한 어노테이션을 작성해주세요.
@Service
public class ReservationService {

    // TODO : 주입 받아야 객체를 작성해주세요.
    private ReservationRepository reservationRepository;
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // TODO : 모든 예약 리스트를 조회하는 코드를 작성해주세요.
    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(Reservation::getReservationDto) // 도메인 → DTO 변환
                .collect(Collectors.toList());
    }

    // TODO : 새로운 예약을 생성하는 코드를 작성해주세요.
    public Reservation createReservation(Long doctorId, Long patientId, LocalDateTime reservationTime) {
        return reservationRepository.save(doctorId, patientId, reservationTime);
    }

    // TODO : 예약을 취소하는 코드를 작성해주세요.
    public void cancelReservation(Long id) {
        reservationRepository.deleteById(id);
    }

//    private void validateReservationTime(LocalTime time) {
//        if (!doctor.isAvailableAt(reservationTime.toLocalTime())) {
//            throw new IllegalArgumentException("해당 시간은 의사의 진료 가능 시간이 아닙니다.");
//        }
//    }
}
