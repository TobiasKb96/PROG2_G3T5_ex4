package at.ac.fhcampuswien.fhmdb.patterns.observerPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Observable {
    private HashMap<NotificationType, List<Observer>> observerMap = new HashMap<>();
    public Observable() {
        for (NotificationType type : NotificationType.values()) {
            observerMap.put(type, new ArrayList<>());
        }
    }
    public void notifyAllSubscribers(NotificationType type) {
        observerMap.get(type).forEach(observer -> observer.update(this));
    }
    public void subscribe(Observer observer, NotificationType type) {
        if (!observerMap.get(type).contains(observer)) {
            observerMap.get(type).add(observer);
        }
    }
    public void unsubscribe(Observer observer, NotificationType type) {
        observerMap.get(type).remove(observer);
    }

}
