import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== \uD83D\uDEAA 스터디룸 예약 시스템: 시간 중복 검증 ===");

        String existingRoom = "A룸";
        LocalTime existingStart = LocalTime.of(14, 0);
        LocalTime existingEnd = LocalTime.of(17, 0);

        System.out.printf("기존 예약 정보: %s (%s ~ %s)\n", existingRoom, existingStart, existingEnd);
        System.out.println("--------------------------------------------------");

        checkBooking(existingRoom, existingStart, existingEnd, "A룸", LocalTime.of(15, 0), LocalTime.of(17, 0));
        checkBooking(existingRoom, existingStart, existingEnd, "C룸", LocalTime.of(14, 0), LocalTime.of(17, 0));
        checkBooking(existingRoom, existingStart, existingEnd, "A룸", LocalTime.of(17, 0), LocalTime.of(20, 0));
        checkBooking(existingRoom, existingStart, existingEnd, "B룸", LocalTime.of(14, 0), LocalTime.of(17, 0));
        checkBooking(existingRoom, existingStart, existingEnd, "A룸", LocalTime.of(14, 0), LocalTime.of(18, 0));
    }

    private static void checkBooking(
            String existRoom, LocalTime existStart, LocalTime existEnd,
            String newRoom, LocalTime newStart, LocalTime newEnd
    ) {
        System.out.printf("신규 예약 요청: %s (%s ~ %s) → ", newRoom, newStart, newEnd);

        if (!existRoom.equals(newRoom)) {
            System.out.println("✅ [예약 성공] 예약이 정상 처리되었습니다.");
            return;
        }

        boolean isOverlapped = newStart.isBefore(existEnd) && newEnd.isAfter(existStart);
        if (isOverlapped) {
            System.out.println("❌ [예약 실패] 이미 해당 시간에 예약이 존재합니다!");
        } else {
            System.out.println("✅ [예약 성공] 예약이 정상 처리되었습니다.");
        }
    }
}
