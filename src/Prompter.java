import java.io.InputStream;
import java.util.Scanner;

public class Prompter {
    private final Scanner scanner;

    public Prompter(InputStream stream) {
        scanner = new Scanner(stream);
    }

    public String prompt(String prompt) {
        System.out.printf("%s > ", prompt);
        return scanner.nextLine();
    }

    public int promptInt(String promptStr) {
        return Integer.parseInt(prompt(promptStr));
    }
}

