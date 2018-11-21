package observer;

public interface IObservable {

    void registerObserver(IObserver o);
    void unregisterObserver(IObserver o);
    void hasChanged();
    void notifyObserver();
    void notifyObserver(Object o);

}
