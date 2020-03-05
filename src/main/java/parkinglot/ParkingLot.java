package parkinglot;


public class ParkingLot implements Comparable {

    private int id;

    private int capacity;

    private int usedCapacity;

    private boolean isFull;

    private Object lock = new Object();

    public ParkingLot(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public synchronized Car getCarByTicket(Ticket ticket) {
        synchronized (lock) {
            capacity--;
            isFull = false;
        }
        return ticket.getCar();
    }

    public Ticket getTicket(Car car) {
        Ticket ticket = Ticket.createNewTicket(id, car);
        return ticket;
    }

    private boolean checkIsFull() {
        return isFull = capacity - usedCapacity == 0;
    }

    public synchronized int remaining() {
        if (checkIsFull()) {
            return 0;
        }
        return ++usedCapacity;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof ParkingLot) {
            ParkingLot parkingLot = (ParkingLot) o;
            if (this.id < parkingLot.id) {
                return -1;
            } else if (this.id == parkingLot.id) {
                return 0;
            }
        }

        return 1;
    }


    public int getId() {
        return id;
    }

    public boolean isFull() {
        return isFull;
    }

}
