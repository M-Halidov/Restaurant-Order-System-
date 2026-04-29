package bg.tu_varna.sit.f24621696.commands.general_commands;

import bg.tu_varna.sit.f24621696.interfaces.Command;

/**
 * Command used for exiting out of the program
 */
public class ExitCommand implements Command {
    /**
     * Returns a signal string indicating the program should exit.
     * @param args Not used.
     * @return A string signaling the program to exit.
     */
    @Override
    public String execute(String[] args) {
        return "Exiting the program...";
    }
}
