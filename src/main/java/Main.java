import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== \uD83D\uDEAA 스터디룸 예약 시스템: 불변 객체 적용 ===");

        String existingRoom = "A룸";
        /*LocalTime existingStart = LocalTime.of(14, 0);
        LocalTime existingEnd = LocalTime.of(17, 0);*/
        ReservationTime existingTime = ReservationTime.of(LocalTime.of(14, 0), LocalTime.of(17, 0));

        System.out.println("기존 예약 정보: " + existingRoom + " (" + existingTime + ")");
        System.out.println("--------------------------------------------------");

        checkBooking(existingRoom, existingTime, "A룸", ReservationTime.of(LocalTime.of(15, 0), LocalTime.of(17, 0)));
        checkBooking(existingRoom, existingTime, "C룸", ReservationTime.of(LocalTime.of(14, 0), LocalTime.of(17, 0)));
        checkBooking(existingRoom, existingTime, "A룸", ReservationTime.of(LocalTime.of(17, 0), LocalTime.of(20, 0)));
        checkBooking(existingRoom, existingTime, "B룸", ReservationTime.of(LocalTime.of(14, 0), LocalTime.of(17, 0)));
        checkBooking(existingRoom, existingTime, "A룸", ReservationTime.of(LocalTime.of(14, 0), LocalTime.of(18, 0)));

        try {
            System.out.print("\n[잘못된 시간 생성 시도 (19:00 ~ 16:00)] → ");
            ReservationTime invalidTime = ReservationTime.of(LocalTime.of(19, 0), LocalTime.of(16, 0));
        } catch (IllegalArgumentException e) {
            System.out.println("❌ [예외 발생] " + e.getMessage());
        }
    }

    private static void checkBooking(
            String existRoom, ReservationTime existTime,
            String newRoom, ReservationTime newTime
    ) {
        System.out.printf("신규 예약 요청: %s (%s) → ", newRoom, newTime);

        if (!existRoom.equals(newRoom)) {
            System.out.println("✅ [예약 성공] 예약이 정상 처리되었습니다.");
            return;
        }

        if (existTime.isOverlappedWith(newTime)) {
            System.out.println("❌ [예약 실패] 이미 해당 시간에 예약이 존재합니다!");
        } else {
            System.out.println("✅ [예약 성공] 예약이 정상 처리되었습니다.");
        }
    }
}
