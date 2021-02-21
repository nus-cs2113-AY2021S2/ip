package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DukeIO {

    private static final String DUKEDATADIR = "data";
    private static final String DUKEDATA = "data/dukeData.txt";

    // create file and directory if they are not created yet
    public static void initialize() throws IOException {
        File dataDirectory = new File(DUKEDATADIR);
        if (!dataDirectory.exists()){
            dataDirectory.mkdirs();
        }
        File dukeData = new File(DUKEDATA);
        if(!dukeData.exists()){
            dukeData.createNewFile();
        }
    }

    public static void writeDukeData() throws IOException {
        // clear existing data in the txt file
        File file = new File(DUKEDATA);
        if(file.exists()){
            file.delete();
        }
        file.createNewFile();
        FileWriter fw = new FileWriter(DUKEDATA);

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
            padding = 3 - taskNum.length();
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
                taskDescription = tasks[i].getDescription() + "/" + ((Deadline) tasks[i]).getKeyWordBeforeDeadline()
                        + " " + ((Deadline) tasks[i]).getDeadline();
                line = taskNum + " [D]" + taskIsDone + taskDescription;
            }else{
                taskDescription = tasks[i].getDescription() + "/" + ((Event) tasks[i]).getKeyWordBeforeDuration()
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

    public static void readDukeData() throws FileNotFoundException {
        String line; //string to read from the txt file(per line)
        Task[] tasks = new Task[100];
        Task task;
        int currentTaskLength = 0;
        File dukeData = new File(DUKEDATA); // create a File for the given file path
        Scanner sc = new Scanner(dukeData); // create a Scanner using the File as the source
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            // identify the task object and create the object according to each line of the txt file
            if(line.substring(4,7).equals("[T]")){
                task = new ToDo(line.substring(11));

            }else if(line.substring(4,7).equals("[D]")){
                task = new Deadline(line.substring(11));
            }else{
                task = new Event(line.substring(11));
            }
            // get the isDone value of the object from the txt file
            if(line.substring(8,9).equals("0")) {
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
