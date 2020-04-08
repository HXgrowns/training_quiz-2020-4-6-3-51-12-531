package exception;

public class ParkingLotFullException extends RuntimeException {
    public ParkingLotFullException(String message) {
        System.out.println(message);
    }
}
