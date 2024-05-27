package at.ac.fhcampuswien.fhmdb.observerPattern;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private List<Observer> observerList = new ArrayList<>();

    public void notifyAllSubscribers(Observable observable) {
        observerList.forEach(observer -> observer.update(observable));
    }
    public void subscribe(Observer observer) {
        observerList.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observerList.remove(observer);
    }

}
