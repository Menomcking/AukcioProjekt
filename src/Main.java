import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private static int rand(int min, int max) {
        return (int)Math.floor(min + (Math.random() * (max - min)));
    }

    private static <T> T choice(List<T> xs) {
        var i = rand(0, xs.size());
        return xs.get(i);
    }

    public static void main(String[] args) throws IOException {
        var prompter = new Prompter(System.in);
        var lines = Files.readAllLines(Paths.get("data.csv"));
        var paintings = new LinkedList<Festmeny>();

        for (var line: lines) {
            var painting = new Festmeny(line);
            paintings.add(painting);
        }

        var n = prompter.promptInt("# of paintings");
        for (var i = 0; i < n; i++) {
            System.out.printf("Painting #%d\n", i + 1);
            paintings.add(new Festmeny(prompter));
            System.out.println();
        }

        for (var i = 0; i < 20; i++) {
            var painting = choice(paintings);
            painting.licit();
        }

        System.out.println(paintings.getFirst());
    }
}
