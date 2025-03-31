package com.example.hospitalreservation.service;

import com.example.hospitalreservation.dto.ReservationDto;
import com.example.hospitalreservation.model.Doctor;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.repository.DoctorRepository;
import com.example.hospitalreservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO : 서비스 레이어에서 필요한 어노테이션을 작성해주세요.
@Service
public class ReservationService {

    private static final Logger log = LoggerFactory.getLogger(ReservationService.class);

    // TODO : 주입 받아야 객체를 작성해주세요.
    private ReservationRepository reservationRepository;
    private final DoctorRepository doctorRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              DoctorRepository doctorRepository) {
        this.reservationRepository = reservationRepository;
        this.doctorRepository = doctorRepository;
    }

    // TODO : 모든 예약 리스트를 조회하는 코드를 작성해주세요.
    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(Reservation::getReservationDto) // 도메인 → DTO 변환
                .collect(Collectors.toList());
    }

    // TODO : 새로운 예약을 생성하는 코드를 작성해주세요.
    public Reservation createReservation(Long doctorId, Long patientId, LocalDateTime reservationTime) {
        //이해했어. 그럼 실제 있는 의사 id인지 확인한 후에 예약생성하는 로직을 예약repository에 만들기 위해서는 reservationRepository와 doctorRepository가 충돌하므로, 한계층 위인 Service계층에서 reservationCreate메서드 내부에서 검증하는게 맞다
        // 의사id 유효성 검증
        Doctor doctor = doctorRepository.findById(doctorId); // 없는 의사 id이면 예외처리

        // 과거시간대로 예약할 수 없음
        if (reservationTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("현재 시간 이후로만 예약할 수 있습니다.");
        }

        // 진료시간 확인
        if (!doctor.isWithinConsultationTime(reservationTime.toLocalTime())) {
            // 의사 진료 가능 시간은 enum에 저장해놨지만, 결국 진료시간의 진짜 주인은 doctor 객체이기 때문에 doctor객체에서 getter을 통해 가져오기로 함
            throw new IllegalArgumentException(
                    "의사의 진료 가능 시간(" + doctor.getConsultationStartTime() + " ~ " + doctor.getConsultationEndTime() + ") 내에서만 예약할 수 있습니다."
            );
        }

        // 예약시간 중복 확인, 지금은 의사 1명이지만 여러명일 경우 각 의사에 대해 예약시간이 중복되는지 확인해야함
        for (Reservation existReservations : reservationRepository.findAll()) {
            if (existReservations.getDoctorId().equals(doctorId) &&
                    existReservations.getReservationTime().equals(reservationTime)) {
                throw new IllegalArgumentException("해당 시간에는 이미 예약이 있습니다. 다른 시간을 선택해주세요.");
            }
        }

        return reservationRepository.save(doctor.getId(), patientId, reservationTime);
    }

    // TODO : 예약을 취소하는 코드를 작성해주세요.
    public void cancelReservation(Long id, String reason) {
        Reservation reservation = reservationRepository.findById(id);
        if (reservation == null) {
            throw new IllegalArgumentException("존재하지 않는 예약입니다.");
        }

        log.info("예약 ID {} 취소됨. 사유: {}", id, reason);
        reservationRepository.deleteById(id);
    }
}
