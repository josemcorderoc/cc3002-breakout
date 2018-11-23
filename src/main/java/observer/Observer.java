package observer;

public interface Observer {

    /**
     * Execute an action when a change from a subject is watched
     * @param subject watched subject
     */
    void update(Subject subject);
}
