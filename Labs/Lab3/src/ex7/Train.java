package ex7;

public class Train {
    public Route r;
    public Schedule s;
    public boolean local;

    public Train(String origin, String destination, int hourDeparture, int hourArrival, int minuteDeparture, int minuteArrival, boolean local) {
        r = new Route(origin, destination);
        s = new Schedule(hourDeparture, hourArrival, minuteDeparture, minuteArrival);
        this.local = local;
    }

    public int calculateTicketPrice() {
        int multiplier = local ? 1 : 2;

        return multiplier * s.calculateDuration();
    }

    public static void main(String args[]) {
        Train t1 = new Train("Bucuresti Nord", "Constanta", 9, 12, 35, 02, true);
        System.out.println(t1.calculateTicketPrice());
        Train t2 = new Train("Bucuresti Nord", "Iasi", 5, 12, 45, 49, true);
        System.out.println(t2.calculateTicketPrice());
        Train t3 = new Train("Bucuresti Nord", "Sofia", 23, 17, 45, 0, true);
        System.out.println(t3.calculateTicketPrice());
    }
}
