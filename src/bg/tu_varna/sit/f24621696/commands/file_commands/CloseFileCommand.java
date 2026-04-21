package bg.tu_varna.sit.f24621696.commands.file_commands;

import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.repos.RepoManager;

public class CloseFileCommand implements Command {
    private RepoManager repoManager;

    public CloseFileCommand(RepoManager repoManager) {
        this.repoManager = repoManager;
    }

    @Override
    public String execute(String[] args) {
        String filePath = repoManager.getFile().getName();
        repoManager.close();

        return "Successfully closed " + filePath + "\n";
    }
}
