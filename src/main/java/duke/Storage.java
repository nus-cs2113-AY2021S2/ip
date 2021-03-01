package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    private File dataFile;
    private String dirName;
    private String fileName;

    public Storage(String filePath) {
        String[] dirFile = filePath.split("/");
        this.dirName = dirFile[0];
        this.fileName = dirFile[1];
        this.filePath = System.getProperty("user.dir") + File.separator + dirName + File.separator + fileName;
        this.dataFile = new File(filePath);
    }

    private ArrayList readFile() throws IOException {
        String localDir = System.getProperty("user.dir");
        Path dirPath = Paths.get(localDir, "data");

        if(!Files.exists(dirPath)) {
            try {
                Files.createDirectory(dirPath);
            } catch (IOException e) {
                System.err.println("Failed to create directory 'data'!" + e.getMessage());
            }
        }

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ArrayList<String> dataItems = (ArrayList) Files.readAllLines(dataFile.toPath(), Charset.defaultCharset());

        return dataItems;
    }

    private ArrayList<Task> parse(ArrayList<String> dataItems) {
        ArrayList<Task> allTasks = new ArrayList<>();
        for (String line : dataItems) {
<<<<<<< Updated upstream
            try {
=======
            try{
>>>>>>> Stashed changes
                String[] tasksInTXT = line.split("\\|");
                String taskType = tasksInTXT[0].trim();
                String taskDone = tasksInTXT[1].trim();
                String taskDescription = tasksInTXT[2].trim();
                switch (taskType) {
                case "T":
                    Todo todo = new Todo(taskDescription);
                    if(taskDone.equals("1")) {
                        todo.setDone(true);
                    }
                    allTasks.add(todo);
                    break;
                case "D":
<<<<<<< Updated upstream
                    Deadline deadline = new Deadline(taskDescription,tasksInTXT[3].trim());
=======
                    Deadline deadline = new Deadline(taskDescription,Parser.parseDate(tasksInTXT[3].trim()));
>>>>>>> Stashed changes
                    if(taskDone.equals("1")) {
                        deadline.setDone(true);
                    }
                    allTasks.add(deadline);
                    break;
                case "E":
<<<<<<< Updated upstream
                    Event event = new Event(taskDescription,tasksInTXT[3].trim());
=======
                    Event event = new Event(taskDescription,Parser.parseDate(tasksInTXT[3].trim()));
>>>>>>> Stashed changes
                    if(taskDone.equals("1")) {
                        event.setDone(true);
                    }
                    allTasks.add(event);
                    break;
                default:
                    System.out.println("Unknown task encountered. Skipping");
                    break;
                }
<<<<<<< Updated upstream
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
            }

=======
            } catch (DateTimeParseException e1) {
                continue;
            }catch (IndexOutOfBoundsException e2) {
                continue;
            }

        }
>>>>>>> Stashed changes
        return allTasks;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> taskList = null;
        try {
            ArrayList<String> dataItems = readFile();
            taskList = parse(dataItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public void writeToTxt(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        for(Task t : tasks) {
            fw.write(t.strAddToTxt());
            fw.write("\n");
        }
        fw.close();
    }

}
