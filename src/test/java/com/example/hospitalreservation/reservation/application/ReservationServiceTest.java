package com.example.hospitalreservation.reservation.application;

import com.example.hospitalreservation.reservation.domain.entity.Reservation;
import com.example.hospitalreservation.reservation.domain.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationServiceTest {

    private ReservationRepository reservationRepository;

    @BeforeEach
    public void setUp() {
        reservationRepository = new ReservationRepository();
    }

    @Test
    public void testFindAllReturnsShallowCopy() throws Exception {
        // given
        LocalDateTime now = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        Reservation reservation = new Reservation(null, 1L, 1L, now);
        Reservation savedReservation = reservationRepository.save(reservation);

        // when
        // findAll() 호출하여 shallow copy 리스트 획득
        List<Reservation> reservationsCopy = reservationRepository.findAll();
        // 저장된 예약 객체를 리스트에서 꺼냄
        Reservation reservationFromList = reservationsCopy.getFirst();

        // Reservation 클래스는 setter가 없으므로 리플렉션을 사용하여 내부 상태 변경
        Field reservationTimeField = Reservation.class.getDeclaredField("reservationTime");
        reservationTimeField.setAccessible(true);
        LocalDateTime newTime = now.plusHours(1);
        reservationTimeField.set(reservationFromList, newTime);

        // then
        Reservation updatedReservation = reservationRepository.findById(savedReservation.getId());
        assertEquals(newTime, updatedReservation.getReservationTime(), "Reservation 객체의 상태가 얕은 복사에 의해 변경");
    }
}
