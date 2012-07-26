package javacity.ui;

/**
 * An interface for an interface! I'm so witty.
 * Enables basic interface functionality for game components
 * so they can tell the user what is going on
 * @author Tom
 */
public interface UserInterface {
    
    public enum MessageType {
        NOTIFICATION, MESSAGE, DISASTER
    }
    
    public void displayMessage(String message, MessageType type)
    
}
