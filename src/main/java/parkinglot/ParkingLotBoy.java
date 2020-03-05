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

    public static void main(String[] args) {

        ParkingLotBoy parkingLotBoy = new ParkingLotBoy();

        Car car1 = new Car("a123456");
        Car car2 = new Car("b123456");
        Car car3 = new Car("c123456");
        Car car4 = new Car("d123456");
        Car car5 = new Car("e123456");
        Car car6 = new Car("f123456");

        Ticket t1 = parkingLotBoy.parking(car1);
        System.out.println(t1.toString());

        Ticket t2 = parkingLotBoy.parking(car2);
        System.out.println(t2.toString());

        Ticket t3 = parkingLotBoy.parking(car3);
        System.out.println(t3.toString());

        Ticket t4 = parkingLotBoy.parking(car4);
        System.out.println(t4.toString());

        Ticket t5 = parkingLotBoy.parking(car5);
        System.out.println(t5.toString());

        Car car = parkingLotBoy.getCar(t1);
        System.out.println(car.getPlateNumber());

        Ticket t6 = parkingLotBoy.parking(car6);
        System.out.println(t6.toString());
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
