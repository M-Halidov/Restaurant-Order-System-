package bg.tu_varna.sit.f24621696.commands.general_commands;

import bg.tu_varna.sit.f24621696.interfaces.Command;

public class ExitCommand implements Command {
    @Override
    public String execute(String[] args) {
        System.exit(0);
        return "";
    }
}
