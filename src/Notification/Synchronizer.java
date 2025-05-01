package Notification;

import java.util.ArrayList;
import java.util.List;

public class Synchronizer {
    private List<Observer> observers = new ArrayList<>();

    public void ajouterObserver(Observer observer) {
        observers.add(observer);
    }

    public void supprimerObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifierObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
