package bg.tu_varna.sit.f24621696.commands.file_commands;

import bg.tu_varna.sit.f24621696.interfaces.Command;
import bg.tu_varna.sit.f24621696.repos.RepoManager;

public class SaveCommand implements Command {
    private RepoManager repoManager;

    public SaveCommand(RepoManager repoManager) {
        this.repoManager = repoManager;
    }

    @Override
    public String execute(String[] args) {
        repoManager.write(repoManager.getFile());
        return "Successfully saved " + repoManager.getFile().getName() + "\n";
    }
}
