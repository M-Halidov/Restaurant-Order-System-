package bg.tu_varna.sit.f24621696.commands.file_commands;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.repos.RepoManager;

import java.io.File;

public class SaveAsCommand implements Command {
    private RepoManager repoManager;

    public SaveAsCommand(RepoManager repoManager) {
        this.repoManager = repoManager;
    }


    @Override
    public String execute(String[] args) {
        if (args.length != 1) {
            throw new CommandException("Invalid amount of arguments!\nopen <file>");
        }

        if(!args[0].endsWith(".bin")) throw new CommandException("Please add .bin after the file name!\nexample: saveas file1.bin");

        String filePath = args[0];
        File file = new File(filePath);
        repoManager.write(file);

        return "Successfully wrote " + filePath + "\n";
    }
}
