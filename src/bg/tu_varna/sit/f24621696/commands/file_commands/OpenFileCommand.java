package bg.tu_varna.sit.f24621696.commands.file_commands;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.repos.RepoManager;

import java.io.File;

/**
 * Command that opens and loads a given file.
 */
public class OpenFileCommand implements Command {
    /**
     * The repository manager providing access to all repositories.
     */
    private RepoManager repoManager;

    /**
     * Constructs an OpenFileCommand with the specified repository manager.
     * @param repoManager Repository manager providing access to all repositories.
     */
    public OpenFileCommand(RepoManager repoManager) {
        this.repoManager = repoManager;
    }

    /**
     * Creates a new file which is then later handled by the backend.
     * @param args The file filename to be loaded.
     * @return A success message with the location of the file.
     * @throws CommandException If the number of arguments is invalid or the filename does not end with suffix ".bin".
     */
    @Override
    public String execute(String[] args) {
        if (args.length != 1) {
            throw new CommandException("Invalid amount of arguments!\nopen <file>");
        }

        if(!args[0].endsWith(".bin")) throw new CommandException("Please add .bin after the file name!\nexample: open file1.bin");

        String filePath = "src/bg/tu_varna/sit/f24621696/resources/" + args[0];
        File file = new File(filePath);
        repoManager.open(file);

        return "Successfully opened " + filePath + "\n";
    }
}
