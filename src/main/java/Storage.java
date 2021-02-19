import java.io.*;
import java.util.Scanner;

public class Storage {

    public void loadFile() throws FileNotFoundException {
        File fileName = new File("taskList.txt");
        Scanner fileReader = new Scanner(fileName);
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();

            if (data.contains("deadline")) {
                loadDeadline(data);
            } else if (data.contains("todo")) {
                loadTodo(data);
            } else if (data.contains("event")) {
                loadEvent(data);
            }
        }
        fileReader.close();

    }

    public void writeToFile(String textToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter("taskList.txt", true);
        fileWriter.write(textToAdd + "\n");
        fileWriter.close();
    }

    public void loadEvent(String inputs) {

        String command = inputs.substring(inputs.indexOf(" ") + 1);
        String title = command.substring(0, command.indexOf("/") - 1);
        String date = inputs.substring(inputs.indexOf("at") + 3);

        Event newEvent = new Event(title, date);
        TaskList.addFromFile(newEvent);
    }

    public void loadDeadline(String inputs) {

        String command = inputs.substring(inputs.indexOf(" ") + 1);
        String title = command.substring(0, command.indexOf("/") - 1);
        String date = inputs.substring(inputs.indexOf("at") + 3);

        Deadline newDeadline = new Deadline(title, date);
        TaskList.addFromFile(newDeadline);
    }

    public void loadTodo(String inputs) {
        String title = inputs.substring(inputs.indexOf(" ") + 1);

        Todo newTodo = new Todo(title);
        TaskList.addFromFile(newTodo);
    }
}
