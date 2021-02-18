package duke;
import java.io.*;

public class Write extends Duke {

    static String home = System.getProperty("user.home");

    public Write(){
    }

    public static void writeFile(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(home+"/tasklist.txt")); //write to this file
            for(String s:taskSentences){
                bw.write(s+"\n");
            }
            bw.close();
        }catch(Exception ex){
            System.out.println("Error writing to file");
        }
    }


}
