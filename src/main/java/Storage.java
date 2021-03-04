import java.io.*;
import java.util.Scanner;

public class Storage {
    public static File filePath;

    public Storage(File filePath) {
        Storage.filePath = filePath;
    }

    public void writeToFile(TaskList tasks) {
        try {
            PrintWriter writer = new PrintWriter("data/duke.txt");
            writer.print("");
            writer.close();
            for (Task i : tasks.getArr()) {
                appendToFile(i.toFileString() + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }

    }


    public void readFile(TaskList tasks, Integer index) {
        try {
            printFileContents(filePath, tasks, index);
        } catch (FileNotFoundException e) {
            System.out.println("no file found");
            File dataDirectory = new File("data");
            dataDirectory.mkdir();
            File dukeFile = new File("data/duke.txt");

        }
    }

    public void createFile() {
    }

    private static void printFileContents(File filePath, TaskList tasks, Integer index) throws FileNotFoundException {
        Scanner s = new Scanner(filePath); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String[] dataEntry = s.nextLine().split("\\|");
            if (dataEntry[0].contains("T")) {
                tasks.add(new Todo(dataEntry[2]));
            } else if (dataEntry[0].contains("D")) {
                tasks.add(new Deadline(dataEntry[2], dataEntry[3]));
            } else if (dataEntry[0].contains("E")) {
                tasks.add(new Event(dataEntry[2], dataEntry[3]));
            }
            if (dataEntry[1].equals("1")) {
                tasks.get(index).markAsDone();
            }
            index++;

        }
    }

    private static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt", true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
}
