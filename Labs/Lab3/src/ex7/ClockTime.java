package ex7;

public class ClockTime {
    public int hour;
    public int minute;

    public ClockTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int compareTime(ClockTime b) {
        if (b.hour > hour) return 1;
        if (b.hour < hour) return -1;
        return 0;
    }
}
