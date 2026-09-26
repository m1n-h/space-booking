import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== \uD83D\uDEAA 스터디룸 예약 시스템 ===");

        ReservationRepository repository = new ReservationRepository();
        ReservationService service = new ReservationService(repository);

        DiscountPolicy fixDiscount = new FixDiscountPolicy(Money.won(2000));
        DiscountPolicy studentDiscount = new StudentDiscountPolicy(0.2);

        Reservation r1 = service.createReservation(
                "RES-001", "A룸",
                ReservationTime.of(LocalTime.of(14, 0), LocalTime.of(17, 0)),
                fixDiscount
        );

        Reservation r2 = service.createReservation(
                "RES-002", "A룸",
                ReservationTime.of(LocalTime.of(17, 0), LocalTime.of(21, 0)),
                studentDiscount
        );

        Reservation r3 = service.createReservation(
                "RES-003", "C룸",
                ReservationTime.of(LocalTime.of(18, 30), LocalTime.of(21, 30)),
                studentDiscount
        );

        Reservation r4 = service.createReservation(
                "RES-004", "B룸",
                ReservationTime.of(LocalTime.of(15, 0), LocalTime.of(20, 0)),
                fixDiscount
        );

        try {
            System.out.println("\n[중복 예약] → A룸 / 15:00 ~ 18:00");
            service.createReservation(
                    "RES-005", "A룸",
                    ReservationTime.of(LocalTime.of(15, 0), LocalTime.of(18, 0)),
                    fixDiscount
            );
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
