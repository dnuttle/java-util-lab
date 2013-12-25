package net.nuttle.java.util.iface;

import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.junit.Test;

public class ObserverTest {

  /**
   * Test the update() method.
   */
  @Test
  public void testUpdate() {
    ObserverImpl observer = new ObserverImpl();
    Observable observable = new ObservableImpl();
    observable.addObserver(observer);
    observable.notifyObservers("NOTIFY");
    assertThat("NOTIFY", is(equalTo(observer.getStatus())));
  }

  private static class ObserverImpl implements Observer {
    private String status = "NONE";

    @Override
    public void update(Observable o, Object arg) {
      status = (String) arg;
    }
    
    public String getStatus() {
      return status;
    }
  }
  
  private static class ObservableImpl extends Observable {
    private List<Observer> observers = new ArrayList<Observer>();
    
    @Override
    public void addObserver(Observer o) {
      observers.add(o);
    }
    
    @Override
    public void notifyObservers() {
      for (Observer o : observers) {
        o.update(this, "NOTIFY");
      }
    }
    
    @Override
    public void notifyObservers(Object arg) {
      for (Observer o : observers) {
        o.update(this, arg);
      }
    }
    
  }

}
