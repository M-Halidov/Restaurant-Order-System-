package bg.tu_varna.sit.f24621696.interfaces;

/**
 * Represents a command that can be executed with a set of arguments.
 */
public interface Command {
    /**
     * Executes the command with the given arguments and returns the result of the operation.
     * @param args arguments given to be handled.
     * @return the result of the command.
     */
    String execute(String[] args);
}
