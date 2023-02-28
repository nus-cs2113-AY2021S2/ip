package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Manages all I/O operations.
 */
public class DukeStorage {

    private static final String DUKE_DATA_DIR = "data";
    private static final String DUKE_DATA_TXT = "data/dukeData.txt";
    private static final int MAXIMUM_LENGTH_OF_TASK_NUMBER = "100".length();
    private static final int INDEX_OF_IS_DONE_VALUE = "1   [T][X".indexOf('X');
    private static final int INDEX_OF_TASK_TYPE = "1   [T".indexOf('T');
    private static final int FIRST_INDEX_OF_TASK_DESCRIPTION = "1   [T][X] S".indexOf('S');

    /**
     * Creates the required text file and the required directory to store the text file in case
     * they are not created yet.
     *
     * @throws IOException If a particular file is not found in the database.
     */
    public static void initialize() throws IOException {
        File dataDirectory = new File(DUKE_DATA_DIR);
        if (!dataDirectory.exists()){
            dataDirectory.mkdirs();
        }
        File dukeData = new File(DUKE_DATA_TXT);
        if(!dukeData.exists()){
            dukeData.createNewFile();
        }
    }

    /**
     * Clears all the current contents in the text file.
     *
     * @throws IOException If a particular file is not found in the database.
     */
    private static void emptyFileContent() throws IOException {
        File file = new File(DUKE_DATA_TXT);
        if(file.exists()){
            file.delete();
        }
        file.createNewFile();
    }

    /**
     * Copy all data regarding the user's tasks from the running program and
     * save them into the text file.
     *
     * @throws IOException If a particular file is not found in the database.
     */
    public static void writeDukeData() throws IOException {
        emptyFileContent();
        FileWriter fw = new FileWriter(DUKE_DATA_TXT);

        String line; //string to write to the txt file(per line)
        String taskNum; //task number
        int padding; //adding certain spaces after the task number
        String taskIsDone;
        String taskDescription;
        Task[] tasks = DukeController.getTasks();
        int currentTaskLength = DukeController.getCurrentTaskLength();

        // format data line by line and write to the txt file
        // example formatting for todo:     1   [T][1] todo eat
        // example formatting for deadline: 35  [D][0] deadline assignment /by 29990930
        // example formatting for event:    100 [E][1] event sleep /on 29991001
        for(int i=0; i<currentTaskLength; i++){
            //common formatting for the 3 task types
            taskNum = String.valueOf(i+1);
            padding = MAXIMUM_LENGTH_OF_TASK_NUMBER - taskNum.length();
            for(int j=0; j<padding; j++){
                taskNum =  taskNum + " ";
            }
            if(tasks[i].getIsDone() == true){
                taskIsDone = "[1] ";
            }else{
                taskIsDone = "[0] ";
            }

            //individual formatting for the 3 task types
            if(tasks[i] instanceof ToDo){
                taskDescription = tasks[i].getDescription();
                line = taskNum + " [T]" + taskIsDone + taskDescription;
            }else if(tasks[i] instanceof Deadline){
                taskDescription = tasks[i].getDescription() + " /" + ((Deadline) tasks[i]).getKeyWordBeforeDeadline()
                        + " " + ((Deadline) tasks[i]).getDeadline();
                line = taskNum + " [D]" + taskIsDone + taskDescription;
            }else{
                taskDescription = tasks[i].getDescription() + " /" + ((Event) tasks[i]).getKeyWordBeforeDuration()
                        + " " + ((Event) tasks[i]).getDuration();
                line = taskNum + " [E]" + taskIsDone + taskDescription;
            }

            //write each line to the txt file
            if(i == currentTaskLength - 1){
                fw.write(line);
            }else{
                fw.write(line + System.lineSeparator());
            }
        }
        fw.close();
    }

    /**
     * Copy all data regarding the user's tasks from the text file and
     * store them into the running program.
     *
     * @throws FileNotFoundException If a particular file is not found in the database.
     */
    public static void readDukeData() throws FileNotFoundException {
        String line; //string to read from the txt file(per line)
        Task[] tasks = new Task[100];
        Task task;
        int currentTaskLength = 0;
        File dukeData = new File(DUKE_DATA_TXT); // create a File for the given file path
        Scanner sc = new Scanner(dukeData); // create a Scanner using the File as the source
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            // identify the task object and create the object according to each line of the txt file
            if(line.substring(INDEX_OF_TASK_TYPE,INDEX_OF_TASK_TYPE+1).equals("T")){
                task = new ToDo(line.substring(FIRST_INDEX_OF_TASK_DESCRIPTION));

            }else if(line.substring(INDEX_OF_TASK_TYPE,INDEX_OF_TASK_TYPE+1).equals("D")){
                task = new Deadline(line.substring(FIRST_INDEX_OF_TASK_DESCRIPTION));
            }else{
                task = new Event(line.substring(FIRST_INDEX_OF_TASK_DESCRIPTION));
            }
            // get the isDone value of the object from the txt file
            if(line.substring(INDEX_OF_IS_DONE_VALUE,INDEX_OF_IS_DONE_VALUE+1).equals("0")) {
                task.setIsDone(false);
            } else {
                task.setIsDone(true);
            }
            // add the task to the array
            tasks[currentTaskLength] = task;
            currentTaskLength++;
        }
        DukeController.setTasks(tasks);
        DukeController.setCurrentTaskLength(currentTaskLength);
    }

}
