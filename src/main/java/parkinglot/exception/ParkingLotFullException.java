package parkinglot.exception;

public class ParkingLotFullException extends RuntimeException {
    public ParkingLotFullException(String message) {
        super(message);
    }
}
