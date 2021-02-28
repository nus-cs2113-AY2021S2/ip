package data;
import duke.todoList;
import java.io.FileWriter;
import java.io.IOException;

public class writeData extends Data{

    public writeData(String path) {
        super(path);
    }

    public writeData(){
    }

    public static void writeToFile(String textToAppend) throws IOException, IOException { // Creates file at provided path and writes provided string to file.
        FileWriter fw = new FileWriter(path);
        fw.write(textToAppend);
        fw.close();
    }

    public static void appendToFile(String textToAppend) throws IOException { // Appends to file at provided path
        FileWriter fw = new FileWriter(path, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }



}
