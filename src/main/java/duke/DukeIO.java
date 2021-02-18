package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DukeIO {

    private static final String DUKEDATADIR = "data";
    private static final String DUKEDATA = "data/dukeData.txt";
    
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

    public static void writeDukeData(){
        String line;
        Task[] tasks = DukeController.getTasks();
        int currentTaskLength = DukeController.getCurrentTaskLength();
        for(int i=0; i<currentTaskLength; i++){
            // xxx [T][0] description

        }
    }

    public void readDukeData() throws FileNotFoundException {
        String line;
        Task[] tasks = new Task[100];
        Task task;
        int currentTaskLength = 0;
        File dukeData = new File(DUKEDATA); // create a File for the given file path
        Scanner sc = new Scanner(dukeData); // create a Scanner using the File as the source
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            // xxx [T][0] description
            if(line.substring(4,7).equals("[T]")){
                task = new ToDo(line.substring(11));

            }else if(line.substring(4,7).equals("[D]")){
                task = new Deadline(line.substring(11));
            }else{
                task = new Event(line.substring(11));
            }
            if(line.substring(8,9).equals("0")) {
                task.setIsDone(false);
            } else {
                task.setIsDone(true);
            }
            tasks[currentTaskLength] = task;
            currentTaskLength++;
        }
        DukeController.setTasks(tasks);
        DukeController.setCurrentTaskLength(currentTaskLength);
    }

    /**
    public static void updateCurrentTaskLength() throws FileNotFoundException {
        String line = "null";
        File dukeData = new File(DUKEDATA); // create a File for the given file path
        Scanner sc = new Scanner(dukeData); // create a Scanner using the File as the source
        while (sc.hasNextLine()) {
            line = sc.nextLine();
        }
        if(line.equals("null")){
            DukeController.setCurrentTaskLength(0);
        }else{
            if(DukeCommandValidator.isInteger(line.substring(0,3))){
                DukeController.setCurrentTaskLength(Integer.parseInt(line.substring(0,3)));
            }else if(DukeCommandValidator.isInteger(line.substring(0,2))){
                DukeController.setCurrentTaskLength(Integer.parseInt(line.substring(0,2)));
            }else{
                DukeController.setCurrentTaskLength(Integer.parseInt(line.substring(0,1)));
            }
        }
    }
     **/


}
