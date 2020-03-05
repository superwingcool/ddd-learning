package parkinglot;

import parkinglot.exception.ErrorTicketException;

import java.util.Date;

public class Ticket {

    private String id;
    private int ParkingLotId;
    private Car car;
    private Date startTime;
    private Date endTime;
    private TicketRule ticketRule = new TicketRule();

    private Ticket(String id, Car car, Date startTime) {
        this.id = id;
        this.car = car;
        this.startTime = startTime;
        this.ParkingLotId = Integer.valueOf(id.split("-")[0]);
    }

    public static Ticket createNewTicket(int id, Car car) {
        Date currentDate = new Date();
        return new Ticket(id + "-" + currentDate.getTime(), car, currentDate);
    }

    public Car getCar() throws ErrorTicketException {
        if(!ticketRule.isValid(this.startTime)) {
            throw new ErrorTicketException(this.id + "is not valid");
        }
        this.endTime = new Date();
        return this.car;
    }

    public int getParkingLotId() {
        return ParkingLotId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", ParkingLotId='" + ParkingLotId + '\'' +
                ", car=" + car +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", ticketRule=" + ticketRule +
                '}';
    }
}
