import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager{
    private final String path;

    public FileManager(){
        this.path = System.getProperty("user.dir") + "/data/duke.txt";
    }

    public static void createFolder(){
        System.out.println("Present project directory is: " + System.getProperty("user.dir"));
        String folderPath = System.getProperty("user.dir") + "/data";
        File folder = new File(folderPath);
        boolean isSuccessful = folder.mkdir();
        if(isSuccessful){
            System.out.println("Folder created successfully.");
        }else{
            System.out.println("Folder already exists");
        }
    }

    public boolean retrieveTextFile(){
        boolean hasTextFile = false;
        try{
            File data = new File(path);
            if(data.createNewFile()){
                System.out.println("Text file created: " + data.getName());
            }else{
                hasTextFile = true;
                System.out.println("Text file already exists.");
            }
        }catch (IOException e){ //creating or retrieving data has errors
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return hasTextFile;
    }

    public void loadData(){
        try{
            File data = new File(path);
            Scanner sc = new Scanner(data);
            while(sc.hasNextLine()){
                String taskToLoad = sc.nextLine();
                DukeCommandHandler.tasks.add(Task.textToTask(taskToLoad));
            }
            sc.close();
        }catch (FileNotFoundException e){
            System.out.println("An Error occurred.");
            e.printStackTrace();
        }
    }

    public void saveData(){
        try{
            FileWriter fileWriter = new FileWriter(path);
            for(int i=0; i<DukeCommandHandler.tasks.size(); ++i){
                //get the task list items
                String taskToWrite = DukeCommandHandler.tasks.get(i).taskToText() + "\n";
                fileWriter.write(taskToWrite);
            }
            fileWriter.close();
        }catch (IOException e){
            System.out.println("An Error occurred.");
            e.printStackTrace();
        }
    }


}