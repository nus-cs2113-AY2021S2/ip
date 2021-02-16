package duke.accessfile;

import java.io.File;
import java.util.Scanner;

public class FileClass {

    public static void createFileObject(){
        File f = new File("data/duke.txt");
        System.out.println("full path: " + f.getAbsolutePath());
        System.out.println("file exists?: " + f.exists());
        System.out.println("is Directory?: " + f.isDirectory());
    }

    public static void printFileContents(String filePath) throws FileNotFoundException, java.io.FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }



}
