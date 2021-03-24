import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * storage of list of taks into a file
 */
public class Storage {

    public static String filePath;

    /**
     * Storage Constructor
     * @param filePath the location of file stored
     */
    public Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     * adding task to file
     * @param lst tasks to be added to file
     */
    public static void writeToFile(TaskList lst){
        try{
            FileWriter fw = new FileWriter(filePath);
            for(Task t : lst.tasks){
                fw.write(t.write() + '\n');
            }
            fw.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * reads the existing tasks in file
     * @param filePath location of file
     * @return the existing tasks in file
     */
    static ArrayList<Task> readFile(String filePath){
        ArrayList<Task> tasks = new ArrayList<>();
        try{
            Scanner fileReader = new Scanner(new File(filePath));
            while(fileReader.hasNextLine()){
                String nextLine = fileReader.nextLine();
                String[] split = nextLine.split("\\|");
                switch(split[0]){
                    case "T":
                        tasks.add(new Todo(split[2]));
                        break;
                    case "D":
                        tasks.add(new Deadline(split[2], LocalDate.parse(split[3])));
                        break;
                    case "E":
                        tasks.add(new Event(split[2], LocalDate.parse(split[3])));
                }
            }
            fileReader.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return tasks;
    }
}