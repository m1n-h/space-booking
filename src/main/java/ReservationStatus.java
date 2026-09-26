public enum ReservationStatus {

    REQUESTED("예약 신청"),
    CONFIRMED("예약 확정"),
    COMPLETED("이용 완료"),
    CANCELLED("예약 취소");

    private final String description;

    ReservationStatus(String description) {
        this.description = description;
    }

    // 현재 상태 → targetStatus 변경 가능 여부 검증 메서드
    public boolean canTransitionTo(ReservationStatus targetStatus) {
        return switch (this) {
            case REQUESTED -> targetStatus == CONFIRMED || targetStatus == CANCELLED;
            case CONFIRMED -> targetStatus == COMPLETED || targetStatus == CANCELLED;
            case COMPLETED, CANCELLED -> false; // 완료/취소 예약 변경 x
        };
    }

    public String getDescription() { return description; }
}
