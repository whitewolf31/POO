package problema3;

import java.util.ArrayList;
import java.util.List;

public class Channel implements Subject {
    private List<Observer> observers;

    public Channel() {
        observers = new ArrayList<Observer>();
    }

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notify(String notification) {
        for (Observer observer: observers) {
            observer.update(notification);
        }
    }
}
