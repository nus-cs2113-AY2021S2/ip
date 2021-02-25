package ip.duke;

import ip.duke.task.Deadline;
import ip.duke.task.Event;
import ip.duke.task.Task;
import ip.duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Storage {
    public static String filePath;
    public static ArrayList<Task> list = new ArrayList<>();

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    public static ArrayList<Task> loadData() throws IOException {
        File dataFile = new File(filePath);
        if (dataFile.createNewFile()) {
            Ui.printLine();
            System.out.println("Since the file does not exist, I have created a new one.");
            Ui.printLine();
        }
        Scanner dataScanner = new Scanner(dataFile);
        while (dataScanner.hasNext()) {
            String data = dataScanner.nextLine();
            String type = data.substring(0, 1);
            boolean isDone = data.charAt(4) == '1';
            String content = data.substring(8);
            String description = content;
            String byTime;
            String atTime;
            int separatePoint = content.length() - 1;
            if (content.contains("|")) {
                separatePoint = content.indexOf("|");
                description = content.substring(0, separatePoint - 1);
            }
            switch (type) {
            case "T":
                fileUpdateTodo(list, description, isDone);
                break;
            case "D":
                byTime = content.substring(separatePoint + 2);
                fileUpdateDeadline(list, description, byTime, isDone);
                break;
            case "E":
                atTime = content.substring(separatePoint + 2);
                fileUpdateEvent(list, description, atTime, isDone);
                break;
            default:
                break;
            }
        }
        return list;
    }

    public static void fileUpdateTodo(ArrayList<Task> list, String description, boolean isDone) {
        list.add(new Todo(description));
        list.get(list.size() - 1).setDone(isDone);
    }

    public static void fileUpdateDeadline(ArrayList<Task> list, String description, String byTime, boolean isDone) {
        list.add(new Deadline(description, byTime));
        list.get(list.size() - 1).setDone(isDone);
    }

    public static void fileUpdateEvent(ArrayList<Task> list, String description, String atTime, boolean isDone) {
        list.add(new Event(description, atTime));
        list.get(list.size() - 1).setDone(isDone);
    }

    public static void saveData() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < TaskList.getSize(); i++) {
            fw.write(TaskList.getList().get(i).toDataString() + "\n");
        }
        fw.close();
    }
}
