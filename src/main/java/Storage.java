import Task.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static String filePath;

    public Storage(String filePath) {
            this.filePath = filePath;
    }

    /***
     * Loads a file from a previously saved location.
     * @param Tasks An arraylist of tasks.
     * @throws FileNotFoundException location of the file does not exist.
     */
    static void readFile(ArrayList<Task> Tasks) throws FileNotFoundException {
        FileInputStream data = new FileInputStream(filePath);
        Scanner sc = new Scanner(data);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split("\\|");
            String type = parts[0].trim();
            String status = parts[1].trim();
            String name = parts[2].trim();
            if (type.equals("T")) {
                Task t = new Todo(name);
                Tasks.add(t);
                if (status.equals("1")) {
                    t.markAsDone();
                }
            } else if (type.equals("D")) {
                String time = parts[3].trim();
                Deadline t = new Deadline(name, time);
                Tasks.add(t);
                if (status.equals("1")) {
                    t.markAsDone();
                }
            } else if (type.equals("E")){
                String time = parts[3].trim();
                Event t = new Event(name, time);
                Tasks.add(t);
                if (status.equals("1")) {
                    t.markAsDone();
                }
            }
        }

    }

    /***
     * Modifies the file
     * @param Tasks Tasks in the arraylist that want to be saved.
     * @throws IOException
     */
    static void writeFile(ArrayList<Task> Tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath,false);
        for (Task t : Tasks) {
            if (t instanceof Todo) {
                writer.write("T | " + t.getStatusNumber().trim() + " | " + t.getDescription().trim());
            }
            else if(t instanceof Event){
                writer.write("E | " + t.getStatusNumber().trim() + " | " + t.getDescription().trim() + " | " + t.getTime().trim());
            }
            else if(t instanceof Deadline){
                writer.write("D | " + t.getStatusNumber().trim() + " | " + t.getDescription().trim() + " | " + t.getTime().trim());
            }
            writer.write("\r\n");
        }
        writer.close();
    }
}
