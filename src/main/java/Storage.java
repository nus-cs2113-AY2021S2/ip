import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Storage {

    public static void saveFile() {
        String filePath = new File("").getAbsolutePath();
        try {
            FileWriter writer = new FileWriter(filePath + "/data.txt");
            ArrayList<Task> tasks = TaskList.getTasks();
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
            System.out.println("ERROR: something went wrong! :(");
        }
    }

    public static void loadFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        ArrayList<Task> tasks = TaskList.getTasks();

        while (scanner.hasNext()) {
            int count = TaskList.getCount();
            String line = scanner.nextLine();
            char type = line.charAt(0);
            String info = line.substring(8);
            int dateIndex = info.indexOf('|');
            switch (type) {
            case 'T':
                Task a = new Todo(info);
                tasks.add(count, a);
                if (line.charAt(4) == '1') {
                    tasks.get(count).markAsDone();
                }
                count++;
                TaskList.setCount(count);
                TaskList.setTasks(tasks);
                break;
            case 'D':
                String dateOrTime = info.substring(dateIndex + 2);
                String updatedInfo = info.substring(0, dateIndex);
                Task b = new Deadline(updatedInfo, dateOrTime);
                tasks.add(count, b);
                if (line.charAt(4) == '1') {
                    tasks.get(count).markAsDone();
                }
                count++;
                TaskList.setCount(count);
                TaskList.setTasks(tasks);
                break;
            case 'E':
                String dateOrTime1 = info.substring(dateIndex + 2);
                String updatedInfo1 = info.substring(0, dateIndex);
                Task c = new Event(updatedInfo1, dateOrTime1);
                tasks.add(count, c);
                if (line.charAt(4) == '1') {
                    tasks.get(count).markAsDone();
                }
                count++;
                TaskList.setCount(count);
                TaskList.setTasks(tasks);
                break;
            default:
                break;
            }
        }
    }
}
