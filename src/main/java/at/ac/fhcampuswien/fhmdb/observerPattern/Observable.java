package at.ac.fhcampuswien.fhmdb.observerPattern;

import java.util.List;

public interface Observable {
    public void notifyAllObservers(List<Observer> observerList);

    public void subscribe(Observer observer);

    public void unsubscribe(Observer observer);

}