package bg.tu_varna.sit.f24621696.exceptions;

/**
 * Exception thrown when a command cannot be executed successfully.
 * This may occur when an invalid amounts of items are provided or the wrong type of data is provided or when there is nothing to operate on.
 */
public class CommandException extends RuntimeException {
    /**
     * Constructs a new CommandException with the specified detail message.
     * @param message the detail message describing the cause of the exception.
     */
    public CommandException(String message) {
        super(message);
    }
}
