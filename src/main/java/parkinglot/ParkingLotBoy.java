package parkinglot;

import parkinglot.exception.ErrorParkingLotException;
import parkinglot.exception.ParkingLotFullException;

import java.util.TreeSet;

public class ParkingLotBoy {

    private static TreeSet<ParkingLot> parkingLots = new TreeSet<>();

    static {
        ParkingLot parkingLot1 = new ParkingLot(1, 2);
        ParkingLot parkingLot3 = new ParkingLot(3, 2);
        ParkingLot parkingLot2 = new ParkingLot(2, 1);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot3);
        parkingLots.add(parkingLot2);
    }


    public Ticket parking(Car car) {
        return getValidateParkingLot().getTicket(car);

    }


    public Car getCar(Ticket ticket) {
        ParkingLot parkingLot = getParkingLotByTicket(ticket);
        return parkingLot.getCarByTicket(ticket);
    }

    private ParkingLot getValidateParkingLot() {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.isFull()) {
                continue;
            }
            int remaining = parkingLot.remaining();
            if (remaining <= 0) {
                continue;
            }
            return parkingLot;
        }

        throw new ParkingLotFullException("The parking lot is full");
    }

    private ParkingLot getParkingLotByTicket(Ticket ticket) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getId() == ticket.getParkingLotId()) {
                return parkingLot;
            }
        }
        throw new ErrorParkingLotException("");
    }

}
