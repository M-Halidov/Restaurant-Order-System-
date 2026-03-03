package bg.tu_varna.sit.f24621696;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.print("> ");
            String line = br.readLine();
            if (line.equals("exit")) break;
            System.out.println("Havent implemented the commands yet. Use exit to stop the program!");

        } while(true);
    }
}
