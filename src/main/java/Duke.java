import java.io.FileNotFoundException;
import Controller.*;
import Tasks.*;
import java.util.ArrayList;
public class Duke {

    private static Storage store = new Storage();
    private static UI ui = new UI();
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public Duke () {
        try {
            tasks = store.loadFile();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------------");
            //tasks = new ArrayList<Task>();
        }
    }

    public void run() {
        ui.welcomeMessage();
        ui.scheduleTimetable(tasks);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}





