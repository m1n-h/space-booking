import java.util.List;

public class ReservationService {

    private final ReservationRepository repository;
    private static final int HOURLY_RATE = 10000;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    public Reservation createReservation(String id, String roomName,ReservationTime time, DiscountPolicy discountPolicy) {
        // 중복 예약 검증
        List<Reservation> existingReservations = repository.findActiveReservationsByRoom(roomName);
        for (Reservation existing : existingReservations) {
            if (existing.getTime().isOverlappedWith(time)) {
                throw new IllegalStateException("❌ 이미 예약된 시간대입니다: " + time);
            }
        }

        // 예약 생성 및 저장
        Reservation reservation = new Reservation(id, roomName, time);
        repository.save(reservation);

        // 요금 계산
        int hours = time.getEndTime().getHour() - time.getStartTime().getHour();
        Money basePrice = Money.won(hours * HOURLY_RATE);
        Money finalPrice = discountPolicy.calculateDiscount(basePrice);

        System.out.printf("✅ [%s] %s (%s) 예약 성공! [기존요금: %s -> 최종결제액: %s]\n",
                id, roomName, time, basePrice, finalPrice);

        return reservation;
    }
}
