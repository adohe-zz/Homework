package com.xqbase.java;

import java.util.Observable;
import java.util.Observer;

/**
 * Design pattern -- Observe Pattern
 *
 * @author Tony He
 */
public class MainRoot {

    public static void main(String[] args) {
        Observer consumer = new Consumer();
        MilkProvider provider = new MilkProvider();
        provider.addObserver(consumer);
        provider.milkProduced();
    }

    static class MilkProvider extends Observable {
        public void milkProduced() {
            setChanged(); // state changed
            notifyObservers();
        }
        public float getPrice() {
            return 2.5f;
        }
    }

    static class Consumer implements Observer {

        @Override
        public void update(Observable o, Object arg) {
            MilkProvider provider = (MilkProvider) o;
            System.out.println("milk price =" + provider.getPrice());
        }
    }
}
