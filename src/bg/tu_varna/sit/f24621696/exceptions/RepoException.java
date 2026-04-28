package bg.tu_varna.sit.f24621696.exceptions;

/**
 * Exception thrown when a repository can not fulfill its duty.
 * This may occur when the item already exists or if it doesn't exist in the repository.
 */
public class RepoException extends RuntimeException {
    /**
     * Constructs a new RepoException with the specified detail message.
     * @param message the detail message describing the cause of the exception.
     */
    public RepoException(String message) {
        super(message);
    }
}
