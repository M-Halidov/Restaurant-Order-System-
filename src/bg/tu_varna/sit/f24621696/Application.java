package bg.tu_varna.sit.f24621696;

import bg.tu_varna.sit.f24621696.commands.CommandHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        CommandHandler commandHandler = new CommandHandler();

        do {
            System.out.print("> ");
            String line = br.readLine().trim();
            commandHandler.processInput(line);
        } while(true);
    }
}
