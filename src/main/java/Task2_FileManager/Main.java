package Task2_FileManager;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final Path ABSOLUTE_PATH = Path.of("C:\\Users\\IAKolosov\\IdeaProjects\\MyDevPlan\\src\\main\\resources\\my_test_file.txt");
    private static final String CONTENT = "Hey guys";
    private static final String UPDATE_CONTENT = "a";

    public static void main(String[] args) {
        try {
            MyTask myTask = new MyTask();
            myTask.mainMethod();
            myTask.getCharsCounter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //read content
    public static void readFileFromPath() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(ABSOLUTE_PATH.toFile()));
        String line = bufferedReader.readLine();
        while (line != null){
            System.out.println(line);
            line = bufferedReader.readLine();
        }
    }

    //replace content
    public static void writeFileToPath() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ABSOLUTE_PATH.toFile()));
        bufferedWriter.write(CONTENT);
        bufferedWriter.close();
    }

    //write formatted text to a file
    public static void writeFormattedFileToPath() throws IOException {
        FileWriter fileWriter = new FileWriter(ABSOLUTE_PATH.toFile());
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("It's so hard\nCan %s help %s?", "you", "me");
        printWriter.close();
    }

    //don't replace, only update
    public static void updateFileToPath() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(ABSOLUTE_PATH.toFile(), "rw");
        randomAccessFile.seek(5);
        randomAccessFile.writeBytes(UPDATE_CONTENT);
        randomAccessFile.close();
    }


    //Reading a File Using Files.lines()
    public static void readingFileUsingLines() throws IOException, URISyntaxException {
        Path path = Paths.get(Objects.requireNonNull(Main.class.getClassLoader()
                .getResource(String.valueOf(ABSOLUTE_PATH))).toURI());

        Stream<String> lines = Files.lines(path);
        String data = lines.collect(Collectors.joining("\n"));
        System.out.println(data);
        lines.close();
    }
}
