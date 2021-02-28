package storage;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File dukeFile;
    private Task task;

    public Storage() {
        createDukeFolder();
        createDukeFile();
    }

    private void createDukeFolder() {
        File dukeFolder = new File("./data");
        if (!dukeFolder.isDirectory()) {
            try {
                dukeFolder.mkdirs();
            } catch (SecurityException e) {
                System.out.println("Directory creation failed");
            }
        }
    }

    public void createDukeFile() {
        File dukeFile = new File("./data/Duke.txt");
        if (!dukeFile.exists()) {
            try {
                dukeFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.dukeFile = dukeFile;
    }

    public void saveTaskListToFile(ArrayList<Task> taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(dukeFile);
        for (Task task : taskList) {
            String descriptionOfTask = task.getDescription().strip();
            boolean isDone = task.isDone();
            String textToAppend = null;
            if (task instanceof Todo) {
                textToAppend = "T|" + isDone + "|" + descriptionOfTask+ "\n";
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline)task;
                textToAppend = "D|" + isDone + "|" + descriptionOfTask + "|" + deadline.getBy().strip() + "\n";
            } else if (task instanceof Event) {
                Event event = (Event)task;
                textToAppend = "E|" + isDone + "|" + descriptionOfTask + "|" + event.getAt().strip() + "\n";
            }
            fileWriter.write(textToAppend);
        }
        fileWriter.close();
    }

    public ArrayList<Task> loadFile() throws FileNotFoundException {
        Scanner sc = new Scanner(dukeFile);
        ArrayList<Task> taskList = new ArrayList<>();
        while (sc.hasNext()){
            String fileText = sc.nextLine();
            String[] taskType = fileText.split("\\|");
            switch (taskType[0]){
            case "E":
                task = new Event(taskType[2], taskType[3]);
                break;
            case "D":
                task = new Deadline(taskType[2], taskType[3]);
                break;
            case "T":
                task = new Todo(taskType[2]);
                break;
            }
            if (taskType[1].equals(true)){
                task.setDone();
            }
            taskList.add(task);
        }
        return taskList;
    }


}


