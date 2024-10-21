package undoFeatureInterface;

/**
 * Undoable interface
 * applied for class that affect the gameplay so that it can be undone.
 */
public interface Undoable {
    /**
     * Save the wanted state
     */
    void save();

    /**
     * Load the previous state
     */
    void undo();
}
