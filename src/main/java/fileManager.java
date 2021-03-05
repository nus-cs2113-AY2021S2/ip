import duke.Task;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class fileManager implements Serializable{
    private String filePath;
    private String file = "duke.txt";
    private FileOutputStream fWrite;
    private ObjectOutputStream oos;
    private FileInputStream fRead;
    private ObjectInputStream ois;

    public fileManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Write objects into file
     * @param tasks serialized data
     */
    public void writeFile(ArrayList<Task> tasks){
        try {
            fWrite = new FileOutputStream(filePath);
            oos = new ObjectOutputStream(fWrite);
            oos.writeObject(tasks);
            oos.close();
        } catch (IOException e) {
                e.printStackTrace();
            }
        }

    /**
     * Load data from file
     * @return  the deserialized data from file and store in a list
     * @throws FileNotFoundException
     */
    public List<Task> readFile() throws FileNotFoundException{
        fRead = new FileInputStream(filePath);
        List<Task> readTasks = new ArrayList<>();
        try {
            if(fRead.available()>0) {
                ois = new ObjectInputStream(fRead);
                if (fRead != null) {
                    readTasks = (List<Task>) ois.readObject();
                }
                ois.close();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return readTasks;
    }


}
