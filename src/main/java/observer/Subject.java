package observer;

/**
 * Models behavior of a observable Subject, according to Observer Pattern
 */
public interface Subject {

    /**
     * Sets subject to be watched by an {@link Observer} (adds an observer in subject list of observers)
     * @param newObserver observer to add
     */
    void registerObserver(Observer newObserver);

    /**
     * Removes a observer from subject observer list
     * @param observerToRemove observer to remove
     */
    void unregisterObserver(Observer observerToRemove);

    /**
     * Gets if there are changes in subject
     * @return true when subject has changed, false when hasn't
     */
    boolean hasChanged();

    /**
     * Set changes as True
     */
    void setChanged();

    /**
     * Notifies all observer if a change happened
     */
    void notifyObserver();

    /**
     * Returns the last available update from subject
     */
    void getUpdate();

}
