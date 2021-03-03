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

    /**
     * open the file or create the file if it not exists,
     * read all lines and store them in an arraylist of strings
     * @return ArrayList which store file strings
     */
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

    /**
     * parse strings to Task objects one by one
     * @param dataItems file content stored in arraylist of strings
     * @return ArrayList<Task> after convert each string to a task
     */
    private ArrayList<Task> parse(ArrayList<String> dataItems) {
        ArrayList<Task> allTasks = new ArrayList<>();
        for (String line : dataItems) {
            try {
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
                    Deadline deadline = new Deadline(taskDescription,Parser.parseDate(tasksInTXT[3].trim()));
                    if(taskDone.equals("1")) {
                        deadline.setDone(true);
                    }
                    allTasks.add(deadline);
                    break;
                case "E":
                    Event event = new Event(taskDescription,Parser.parseDate(tasksInTXT[3].trim()));
                    if(taskDone.equals("1")) {
                        event.setDone(true);
                    }
                    allTasks.add(event);
                    break;
                default:
                    System.out.println("Unknown task encountered. Skipping");
                    break;
                }
            } catch (DateTimeParseException e1) {
                continue;
            }catch (IndexOutOfBoundsException e2) {
                continue;
            }
        }

        return allTasks;
    }

    /**
     * load the tasks stored in the file
     * @return ArrayList<Task> store the tasks into an arraylist
     */
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

    /**
     * Synchronise the file after modified the tasks.
     * overwrite to the file with tasks passed in.
     * @param tasks new arraylist of tasks which will be written to the file
     * @throws IOException if write to file failed
     */
    public void writeToTxt(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        for(Task t : tasks) {
            fw.write(t.strAddToTxt());
            fw.write("\n");
        }
        fw.close();
    }

}
