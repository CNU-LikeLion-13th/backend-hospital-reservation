package com.example.hospitalreservation.service;

import com.example.hospitalreservation.FeeCalFactory;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// TODO : 서비스 레이어에서 필요한 어노테이션을 작성해주세요.
@Service
public class ReservationService {

    // TODO : 주입 받아야 객체를 작성해주세요
    private final ReservationRepository reservationRepository;
    private final FeeCalFactory feeCalFactory;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
        this.feeCalFactory = new FeeCalFactory();
    }

    // TODO : 모든 예약 리스트를 조회하는 코드를 작성해주세요.
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // TODO : 새로운 예약을 생성하는 코드를 작성해주세요.
    public void createReservation(Reservation reservation) {
        int hour = reservation.reservationTime.getHour();
        if (hour < 9 || hour >= 17) {
            throw new IllegalArgumentException("의사의 진료 가능 시간(09:00~17:00) 내에서만 예약할 수 있습니다.");
        }

        for (Reservation r : reservationRepository.findAll()) {
            if (r.doctorId.equals(reservation.doctorId) && r.reservationTime.equals(reservation.reservationTime)) {
                throw new IllegalArgumentException("해당 시간에는 이미 예약이 있습니다. 다른 시간을 선택해주세요.");
            }
        }
        reservationRepository.save(reservation);
    }

    // TODO : 예약을 취소하는 코드를 작성해주세요.
    public void cancelReservation(Long id, String reason) {
        boolean remove = reservationRepository.deleteById(id);
        if (!remove){
            throw new IllegalArgumentException("존재하지 않는 예약입니다.");
        }
    }

    //예약 진료비 계산 코드
    public int calculateFee(Reservation reservation) {
        return feeCalFactory.calculateFee(reservation.reason);
    }
}
