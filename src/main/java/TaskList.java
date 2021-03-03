import java.io.*;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Tasks> tasks = new ArrayList<>();
    private InputStream input = null;
    public TaskList() {
        try {
            FileReader file = new FileReader("Data/duke.txt");
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

    public ArrayList<Tasks> getList(){
        return tasks;
    }

    private Tasks getTask(String line){
        Tasks task;
        if (line.charAt(1) == 'T'){
            task = new ToDo(line.substring(6), true);
        } else if (line.charAt(1) == 'D'){
            int index = line.indexOf("(by:");
            task = new Deadline(line.substring(6, index), line.substring(index + 5));
        } else {
            int index = line.indexOf("(at:");
            task = new Event(line.substring(6, index), line.substring(index + 5));
        }
        if (line.charAt(4) == '✓'){
            task.setDone();
        }
        return task;
    }

    public void update(Tasks task){
        tasks.add(task);
    }

    public Tasks get(int i){
        return tasks.get(i - 1);
    }

    public void delete(int i){
        tasks.remove(i - 1);
    }

    public void updateStatus(int i){
        tasks.get(i - 1).setDone();
    }

    public int getSize(){
        return tasks.size();
    }

    public void save(){
        StringBuilder line = new StringBuilder();
        for (Tasks task : tasks){
            String append = task.toString() + "\n";
            line.append(append);
        }
        String folderPath = "data";
        File directory = new File(folderPath);
        try{
            if (!directory.isDirectory()){
                File folder = new File(folderPath);
                if (!folder.mkdir()){
                    System.out.println("cannot make a folder");
                }
            }
            File file = new File("./data/duke.txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(line.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("No File found");
        }
    }

    public String toString(){
        StringBuilder line = new StringBuilder();
        for (Tasks task : tasks) {
            line.append(task.toString());
            line.append('\n');
        }
//        System.out.println(tasks);
        return line.toString();
    }

    public String toStr(){
        StringBuilder line = new StringBuilder();
        int i = 1;
        for (Tasks task : tasks) {
            line.append(i);
            line.append(".");
            line.append(task.toString());
            line.append('\n');
            i++;
        }
//        System.out.println(tasks);
        return line.toString();
    }

    public static void main(String[] args) throws IOException {
        TaskList task = new TaskList();
    }
}