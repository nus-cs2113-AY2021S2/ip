package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {
    private Path dataDirectory;

    private Path dataFileDirectory;

    /**
     * A constructor for Storage class
     */
    public Storage(){
        this.dataDirectory = Path.of(System.getProperty("user.dir") + "\\data");
        this.dataFileDirectory = Path.of(System.getProperty("user.dir") + "\\data" + "\\duke.txt");
    }

    /**
     * Performs a write to disk operation.
     *
     * @param tasks an input arraylist of tasks, contents which are to be written to the disk, overwriting whatever
     *              is currently present
     */
    public void writeToDisk(ArrayList<Task> tasks) throws IOException {
        //Each time I want to write to the Disk, I clear away the entire contents of the data file and write everything
        //Done by deleting the file and creating it again
        File clear = new File(String.valueOf(this.dataFileDirectory));
        clear.delete();
        createNewFile();

        //To write to the new file, I will append 1 task from the ArrayList at a time
        FileWriter fw = new FileWriter(String.valueOf(this.dataFileDirectory), true);
        for(Task task : tasks){
            String temp = "";

            if(task.getDoneStatus()){
                temp += "1|";
            } else {
                temp += "0|";
            }
            temp += task.getType() + "|" + task.getDescription() + "|" + task.getSeparator() + "|";
            if(task instanceof Event) {
                temp += ((Event) task).getAt();
            } else if(task instanceof Deadline){
                temp += ((Deadline) task).getBy();
            } else {
                temp += "";
            }
            temp += "\n";
            fw.write(temp);
        }
        fw.close();
    }

    /**
     * A wrapper method for the writeToDisk method
     *
     * @param tasks an input arraylist of tasks, contents which are to be written to the disk, overwriting whatever
     *              is currently present
     */
    public void wrapWriteToDisk(ArrayList<Task> tasks){
        try{
            writeToDisk(tasks);
        } catch (IOException e) {
            System.out.println("Issues with writing to .txt file");
        }
    }

    /**
     * Performs a load from disk operation.
     *
     * @param taskList an input TaskList, which is updated from the contents in the disk
     */
    public void loadDataFromDisk(TaskList taskList) throws FileNotFoundException {
        File dataFile = new File(String.valueOf(dataFileDirectory));
        Scanner s = new Scanner(dataFile);

        while(s.hasNext()){
            //format I am using is, to use index 0 to store the status.
            //E.g:
            //1|event|memes   r|/at|   next Friday
            String temp = s.nextLine();
            String[] command = temp.split("\\|");
            Task tempTask = taskList.populateArrayList(Arrays.copyOfRange(command,1, command.length), taskList.getTasks());
            if (command[0].equals("1")){
                tempTask.setAsDone();
            }
        }
        s.close();
    }

    /**
     * Creates a new text file at a specified directory
     */
    public void createNewFile() {
        try{
            // If directory does not exist, then create the directory
            // If directory exists already, just does nothing
            checkForDirectory();
        } catch (IOException e) {
            System.out.println("A serious error has occurred");
        }

        try{
            // If file does not exist, then create the file
            checkForFile();
        } catch (IOException e) {
            System.out.println("A serious error has occurred");
        }
    }

    /**
     * Checks if the text file exists, and creates one otherwise
     */
    public void checkForFile() throws IOException {
        if(!Files.exists(this.dataFileDirectory)){
            File newFile = new File(String.valueOf(this.dataFileDirectory));
            newFile.createNewFile();
        }
    }

    /**
     * Checks if the directory to store the text file exists, and creates one otherwise
     */
    public void checkForDirectory() throws IOException {
        if(!Files.exists(this.dataDirectory)){
            Files.createDirectory(this.dataDirectory);
        }
    }
}
