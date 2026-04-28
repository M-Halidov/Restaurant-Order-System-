package bg.tu_varna.sit.f24621696.exceptions;

/**
 * Exception thrown when a file cannot be read or written to.
 */
public class FileException extends RuntimeException {
    /**
     * Constructs a new FileException with the specified detail message.
     * @param message the detail message describing the cause of the exception.
     */
    public FileException(String message) {
        super(message);
    }
}
