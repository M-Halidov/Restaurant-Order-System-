package bg.tu_varna.sit.f24621696;

import bg.tu_varna.sit.f24621696.commands.CommandHandler;
import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.exceptions.MenuItemException;
import bg.tu_varna.sit.f24621696.exceptions.TableException;

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
            String result="";
            try {
                result = commandHandler.processInput(line);
            } catch (CommandException | MenuItemException | TableException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(result);
        } while(true);
    }
}
