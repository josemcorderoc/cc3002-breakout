package visitor;

/**
 * Defines a visitable object (according to Visitor Pattern)
 *
 * @author Jose Miguel Cordero
 */
public interface Visitable {

    /**
     * Performs the operation defined by the visitor
     * @param visitor operation
     */
    void accept(Visitor visitor);

}
