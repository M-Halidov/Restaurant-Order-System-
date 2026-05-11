package bg.tu_varna.sit.f24621696.commands.file_commands;

import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.repos.RepoManager;

/**
 * Command which closes the currently open file.
 */
public class CloseFileCommand implements Command {
    /**
     * The repository manager providing access to all repositories.
     */
    private RepoManager repoManager;

    /**
     * Constructs CloseFileCommand with the specified repository manager.
     * @param repoManager Repository manager providing access to all repositories.
     */
    public CloseFileCommand(RepoManager repoManager) {
        this.repoManager = repoManager;
    }

    /**
     * Closes the currently open file and returns a confirmation message.
     * @param args Not used.
     * @return A success message with the file path of the closed file.
     */
    @Override
    public String execute(String[] args) {
        String filePath = repoManager.getFile().getName();
        repoManager.close();

        return "Successfully closed " + filePath + "\n";
    }
}
