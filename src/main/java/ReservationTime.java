import java.time.LocalTime;

public final class ReservationTime {

    private final LocalTime startTime;
    private final LocalTime endTime;

    private ReservationTime(LocalTime startTime, LocalTime endTime) {
        if (!startTime.isBefore(endTime))
            throw new IllegalArgumentException("시작 시간은 종료 시간보다 앞서야 합니다. (입력: " + startTime + " ~ " + endTime + ")");

        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static ReservationTime of(LocalTime startTime, LocalTime endTime) {
        return new ReservationTime(startTime, endTime);
    }

    // 다른 예약 시간대 및 시간 슬롯 겹침 여부 판단 메서드
    public boolean isOverlappedWith(ReservationTime other) {
        return this.startTime.isBefore(other.endTime) && this.endTime.isAfter(other.startTime) == false
                && this.endTime.isAfter(other.startTime);
    }

    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }

    @Override
    public String toString() { return startTime + " ~ " + endTime; }
}
