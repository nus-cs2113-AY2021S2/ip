import java.io.*;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    protected static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + "\n");
        fw.close();
    }
    public static void replaceTXT(String name) throws IOException {
        File file = new File("C:/Users/XPS/Desktop/Uni drives me crazy/Y2S2/NUS exchange/CS2113 Software Engineering/ip/duke.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        String oldtext = "";
        while ((line = reader.readLine()) != null) {
            oldtext += line + "\r\n";
        }
        reader.close();
        String replacedtext = oldtext.replace("0 | " + name , "1 | " + name);
        FileWriter writer = new FileWriter("C:/Users/XPS/Desktop/Uni drives me crazy/Y2S2/NUS exchange/CS2113 Software Engineering/ip/duke.txt");
        writer.write(replacedtext);
        writer.close();
    }

}
