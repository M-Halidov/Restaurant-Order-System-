package bg.tu_varna.sit.f24621696.interfaces;

/**
 * Represents a command that can be executed with a set of arguments.
 */
public interface Command {
    /**
     * Executes the command with the given arguments and returns the result of the operation.
     * @param args Arguments given to be handled.
     * @return The result of the command.
     */
    String execute(String[] args);
}
