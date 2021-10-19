package ex7;

public class Schedule {
    private ClockTime departure;
    private ClockTime arrival;

    public Schedule(int hourDeparture, int hourArrival, int minuteDeparture, int minuteArrival) {
        departure = new ClockTime(hourDeparture, minuteDeparture);
        arrival = new ClockTime(hourArrival, minuteArrival);
    }

    public int calculateDuration() {
        int bigger = departure.compareTime(arrival);
        ClockTime biggerTime = arrival;
        ClockTime smallerTime = departure;
        boolean substract = false;
        if (bigger == 0) return 0;
        if (bigger < 0) {
            biggerTime = departure;
            smallerTime = arrival;
            substract = true;
        }
        int timeDiff;
        if (biggerTime.minute < smallerTime.minute) {
            timeDiff = (biggerTime.hour - smallerTime.hour - 1) * 60 + 60 - smallerTime.minute + biggerTime.minute;
        } else {
            timeDiff = (biggerTime.hour - smallerTime.hour) * 60 + biggerTime.minute - smallerTime.minute;
        }

        if(substract) return 24 * 60 - timeDiff;

        return timeDiff;
    }
}
