package ex7;

public class Route {
    private String origin;
    private String destination;

    public Route(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public boolean twoWay(Route tester) {
        return origin.equals(tester.destination) && destination.equals(tester.origin);
    }
}
