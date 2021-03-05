

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This is the class which we use to store the value so of the task list onto our hardisk.
 */

public class Storage {
    final private String filePath = "data/duke.txt";
    private TaskList list;
    private ArrayList<Task> tasks = new ArrayList<>();
    public Storage () {
        try {
            FileReader file = new FileReader(this.filePath);
            BufferedReader reader = new BufferedReader(file);
            String line;
            while ((line = reader.readLine()) != null){
                tasks.add(getTask(line));
            }
            file.close();
        } catch (IOException e){
            tasks = new ArrayList<>();
        }
    }

    private Task getTask(String line){
        Task task;
        char check = line.charAt(1);
        switch(check){
        case 'T':
            task = new ToDos(line.substring(6));
            break;
        case 'D':
            int index = line.indexOf("|");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime date = LocalDateTime.parse(line.substring(index + 1).trim(), formatter);
            task = new Deadline(line.substring(6, index), date);
            break;
        default:
            int index1 = line.indexOf("|");
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime date1 = LocalDateTime.parse(line.substring(index1 + 1).trim(), formatter1);
            task = new Event(line.substring(6, index1), date1);
            break;
        }
        if (line.charAt(4) != '✗'){
            task.updateStatus();
        }
        return task;
    }
    // loads the elements of the task list
    public TaskList load(){
        this.list = new TaskList(this.tasks) ;
        return this.list;
    }
    // saves the task onto the text file.
    public void save(){
        try {
            String folderPath = "Data";
            File directory = new File(folderPath);
            if (!directory.isDirectory()){
                File folder = new File(folderPath);
                if (!folder.mkdir()){
                    System.out.println("cannot make a folder");
                }
            }
            File file = new File(this.filePath);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(list.save());
            writer.close();
        } catch (IOException e) {
            System.out.println("No File found");
        }
    }
}
