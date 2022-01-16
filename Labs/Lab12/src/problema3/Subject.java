package problema3;

public interface Subject {
    public void subscribe(Observer observer);

    public void unsubscribe(Observer observer);

    public void notify(String notification);
}
