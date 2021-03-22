import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * File handling
 */
public class Storage {
    private static TaskList tasks = new TaskList();
    private static Task[] t = tasks.getTasks();
    private static Ui ui = new Ui();
    private static String filePath;

    /**
     * constructor of the class
     */
    public Storage(){}
    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }



    /**
     * Load file from local drive
     */

    public static void createFile(){
        System.out.println("Creating file...");
        File tempDir = new File(System.getProperty("user.dir"));
        File f = new File(tempDir,"duke.txt");
        System.out.println("File is created at "+ f.getAbsoluteFile());
        filePath = f.getAbsolutePath();
    }

    public static void loadFile() {
        String dir = System.getProperty("user.dir");
        try {
            printFileContents(dir+"duke.txt");
        } catch (FileNotFoundException e) {
            createFile();
        }

    }


    public static void clearFile(){
        try{
            writeToFile(filePath, " ");
        }catch(IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }
    private static void writeToFile(String filePath, String textToAdd) throws IOException {

        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }


    /**
     * Write to file or append to file
     * @param num total number of tasks
     */
    public static void changeFile(int num) {

        if(num>=1){
            try{
                writeToFile(filePath,"[" + t[1].getStatusIcon() + "] " + t[1].getDescription() + "\n");
            }catch (IOException e){
                System.out.println("Something went wrong: " + e.getMessage());
            }

        }

        for (int i = 2; i <= num; i++) {
            try {
                    appendToFile(filePath, "[" + t[i].getStatusIcon() + "] " + t[i].getDescription() + "\n");
            } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
            }
        }







    }

}
