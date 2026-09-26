import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {

    private final List<Reservation> reservations = new ArrayList<>();

    public void save(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> findActiveReservationsByRoom(String roomName) {
        return reservations.stream()
                .filter(r -> r.getRoomName().equals(roomName))
                .filter(r -> r.getStatus() == ReservationStatus.REQUESTED || r.getStatus() == ReservationStatus.CONFIRMED)
                .toList();
    }
}
