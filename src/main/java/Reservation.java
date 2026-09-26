public class Reservation {

    private final String id;
    private final String roomName;
    private final ReservationTime time;
    private ReservationStatus status;

    public Reservation(String id, String roomName, ReservationTime time) {
        this.id = id;
        this.roomName = roomName;
        this.time = time;
        this.status = ReservationStatus.REQUESTED;
    }

    public void changeStatus(ReservationStatus newStatus) {
        if (!this.status.canTransitionTo(newStatus)) {
            throw new IllegalStateException(
                    String.format("[%s] 상태에서는 [%s] 상태로 변경할 수 없습니다.",
                        this.status.getDescription(), newStatus.getDescription())
            );
        }

        this.status = newStatus;
    }

    public String getId() { return id; }
    public String getRoomName() { return roomName; }
    public ReservationTime getTime() { return time; }
    public ReservationStatus getStatus() { return status; }

    @Override
    public String toString() {
        return String.format("예약[%s] | %s | %s | 상태: %s", id, roomName, time, status.getDescription());
    }
}
