package bg.tu_varna.sit.f24621696.commands.file_commands;

import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.repos.RepoManager;

/**
 * Command that stores data to the currently open file.
 */
public class SaveCommand implements Command {
    /**
     * The repository manager providing access to all repositories.
     */
    private RepoManager repoManager;

    /**
     * Constructs an SaveCommand with the specified repository manager.
     * @param repoManager Repository manager providing access to all repositories.
     */
    public SaveCommand(RepoManager repoManager) {
        this.repoManager = repoManager;
    }

    /**
     * Stores the current state of data to the currently open file
     * @param args Not used.
     * @return A success message detailing the file name.
     */
    @Override
    public String execute(String[] args) {
        repoManager.write(repoManager.getFile());
        return "Successfully saved " + repoManager.getFile().getName() + "\n";
    }
}
