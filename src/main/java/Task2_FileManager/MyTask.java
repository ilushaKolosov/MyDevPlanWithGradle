package Task2_FileManager;

import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class MyTask {
    private final Path PATH = Path.of("C:\\Users\\IAKolosov\\IdeaProjects\\MyDevPlan\\src\\main\\resources\\my_test_file.txt");
    private int charsCounter = 0;

    public void mainMethod() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(PATH.toFile()));
        StringBuilder contentBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            contentBuilder.append(line).append(System.lineSeparator());
        }
        reader.close();
        String content = contentBuilder.toString();

        String modifiedContent = modifyContent(content);

        BufferedWriter writer = new BufferedWriter(new FileWriter(PATH.toFile()));
        writer.write(modifiedContent);
        writer.close();
    }

    public void getCharsCounter() {
        System.out.printf("This file has only %d characters", charsCounter);
    }

    public String modifyContent(String content) {
        StringBuilder result = new StringBuilder();
        Scanner scanner = new Scanner(content);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] modifiedLine = removeEmptySpaceAndCount(line);
            result.append(Arrays.toString(modifiedLine)).append(System.lineSeparator());
        }
        scanner.close();
        return result.toString();
    }

    public String[] removeEmptySpaceAndCount(String line) {
        int counter = 0;
        Scanner scanner = new Scanner(line);
        while (scanner.hasNext()) {
            String word = scanner.next();
            charsCounter += word.length();
            counter++;
        }
        int emptySpaceCounter = counter - 1;
        line = line.trim();
        scanner.close();

        return new String[]{line, "// in that row was " + emptySpaceCounter + " empties"};
    }

}
