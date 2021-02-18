import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileManager {
    public static final String root = System.getProperty("user.dir");
    public static final Path filePath = Paths.get(root, "data", "duke.txt");
    public static final Path dirPath = Paths.get(root, "data");


    public static void saveFile() {
        try {
            FileWriter writer = new FileWriter(filePath.toString());
            ArrayList<Task> tasks = TaskManager.getTasks();
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    if (task.getStatusIcon().equals("[T][x] ")) {
                        writer.write("T | 1 | " + task.getDescription() + System.lineSeparator());
                    } else {
                        writer.write("T | 0 | " + task.getDescription() + System.lineSeparator());
                    }
                } else if (task instanceof Deadline) {
                    if (task.getStatusIcon().equals("[D][x] ")) {
                        writer.write("D | 1 | " + task.getDescription() + "| " + ((Deadline) task).getBy() + System.lineSeparator());
                    } else {
                        writer.write("D | 0 | " + task.getDescription() + "| " + ((Deadline) task).getBy() + System.lineSeparator());
                    }
                } else if (task instanceof Event) {
                    if (task.getStatusIcon().equals("[E][x] ")) {
                        writer.write("E | 1 | " + task.getDescription() + "| " + ((Event) task).getAt() + System.lineSeparator());
                    } else {
                        writer.write("E | 0 | " + task.getDescription() + "| " + ((Event) task).getAt() + System.lineSeparator());
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFile() throws IOException {
        File fileDirectory = new File(dirPath.toString());

        if (!fileDirectory.exists()) {
            fileDirectory.mkdir();
        }

        File dataFile = new File(filePath.toString());
        dataFile.createNewFile();
        Scanner scanner = new Scanner(dataFile);

        while (scanner.hasNext()) {
            ArrayList<Task> tasks = TaskManager.getTasks();
            int count = TaskManager.getCount();
            String line = scanner.nextLine();
            char type = line.charAt(0);
            String info = line.substring(8);
            int dateIndex = info.indexOf('|');
            switch (type) {
            case 'T':
                Task a = new Todo(info);
                tasks.add(count,a);
                if (line.charAt(4) == '1') {
                    tasks.get(count).markAsDone();
                }
                count++;
                TaskManager.setCount(count);
                TaskManager.setTasks(tasks);
                break;
            case 'D':
                String dateOrTime = info.substring(dateIndex + 2);
                String updatedInfo = info.substring(0, dateIndex );
                Task b = new Deadline(updatedInfo, dateOrTime);
                tasks.add(count,b);
                if (line.charAt(4) == '1') {
                    tasks.get(count).markAsDone();
                }
                count++;
                TaskManager.setCount(count);
                TaskManager.setTasks(tasks);
                break;
            case 'E':
                String dateOrTime1 = info.substring(dateIndex + 2);
                String updatedInfo1 = info.substring(0, dateIndex );
                Task c = new Event(updatedInfo1, dateOrTime1);
                tasks.add(count,c);
                if (line.charAt(4) == '1') {
                    tasks.get(count).markAsDone();
                }
                count++;
                TaskManager.setCount(count);
                TaskManager.setTasks(tasks);
                break;
            default:
                break;
            }
        }
    }
}
