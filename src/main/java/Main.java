import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== \uD83D\uDEAA 스터디룸 예약 시스템: Enum 상태 관리 ===");

        //String existingRoom = "A룸";
        /*LocalTime existingStart = LocalTime.of(14, 0);
        LocalTime existingEnd = LocalTime.of(17, 0);*/
        //ReservationTime existingTime = ReservationTime.of(LocalTime.of(14, 0), LocalTime.of(17, 0));

        /*System.out.println("기존 예약 정보: " + existingRoom + " (" + existingTime + ")");
        System.out.println("--------------------------------------------------");

        checkBooking(existingRoom, existingTime, "A룸", ReservationTime.of(LocalTime.of(15, 0), LocalTime.of(17, 0)));
        checkBooking(existingRoom, existingTime, "C룸", ReservationTime.of(LocalTime.of(14, 0), LocalTime.of(17, 0)));
        checkBooking(existingRoom, existingTime, "A룸", ReservationTime.of(LocalTime.of(17, 0), LocalTime.of(20, 0)));
        checkBooking(existingRoom, existingTime, "B룸", ReservationTime.of(LocalTime.of(14, 0), LocalTime.of(17, 0)));
        checkBooking(existingRoom, existingTime, "A룸", ReservationTime.of(LocalTime.of(14, 0), LocalTime.of(18, 0)));*/

        Reservation reservation = new Reservation(
                "RES-001",
                "A룸",
                ReservationTime.of(LocalTime.of(14, 0), LocalTime.of(17, 0))
        );

        System.out.println("최초 생성: " + reservation);

        reservation.changeStatus(ReservationStatus.CONFIRMED);
        System.out.println("승인 완료: " + reservation);

        reservation.changeStatus(ReservationStatus.COMPLETED);
        System.out.println("이용 완료: " + reservation);

        try {
            /*System.out.print("\n[잘못된 시간 생성 시도 (19:00 ~ 16:00)] → ");
            ReservationTime invalidTime = ReservationTime.of(LocalTime.of(19, 0), LocalTime.of(16, 0));*/
            System.out.print("\n[잘못된 상태 변경 생성 시도 (이용 완료 → 예약 취소)] → ");
            reservation.changeStatus(ReservationStatus.CANCELLED);
        } catch (IllegalStateException e) {
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
