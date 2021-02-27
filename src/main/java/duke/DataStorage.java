package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataStorage {

    /**
     * Reads data.txt line by line and passes each line of save data
     * as a string to loadData()
     * Creates a new file named data.txt if it does not exist
     *
     * @param list Empty ArrayList to be passed to loadData()
     */
    public static void readFile(ArrayList<Task> list) {
        try {
            File saveData = new File(Constants.FILENAME);
            if(saveData.createNewFile()) { //file is created
                System.out.println("data.txt created");
            } else { //file already exists
                Scanner reader = new Scanner(saveData);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    loadData(list, line);
                }
            }
        } catch (IOException e) {
            System.out.print(Constants.LINE + Constants.GENERIC_ERROR_MESSAGE + Constants.LINE);
        }
    }

    /**
     * Reads the line of sava data and adds tasks accordingly to the ArrayList containing tasks
     *
     * @param list Arraylist which tasks are to be added as they are parsed
     * @param line line of save data
     */
    private static void loadData(ArrayList<Task> list, String line) {
        Object[] parsedData = Parser.dataParser(line);
        String desc = (String)parsedData[0];
        boolean isDone = (boolean)parsedData[1];
        String date = (String)parsedData[2];

        switch (line.charAt(1)) {
        case 'T':
            list.add(new ToDo(desc, isDone));
            break;
        case 'D':
            list.add(new Deadline(desc, isDone, date));
            break;
        case 'E':
            list.add(new Event(desc, isDone, date));
            break;
        }
    }

    /**
     * Writes save data to data.txt
     *
     * @param list ArrayList containing tasks
     */
    public static void writeSaveData(ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter(Constants.FILENAME);
            fw.write(composeOutput(list.get(0)) + System.lineSeparator());

            for (int i = 1; i< list.size(); i++) {
                fw.append(composeOutput(list.get(i))).append(System.lineSeparator());
            }
            fw.close();

        } catch (IOException e) {
            System.out.print(Constants.LINE + Constants.GENERIC_ERROR_MESSAGE + Constants.LINE);
        }
    }

    /**
     * Composes a line of save data for the task passed
     *
     * @param data Task to be saved
     * @return String of save data to be written into data.txt
     */
    private static String composeOutput(Task data) {
        boolean isDone = data.getStatus();
        StringBuilder output = new StringBuilder();

        if(!isDone) {
            output.append("0");
        } else {
            output.append("1");
        }

        switch(data.toString().charAt(2)){
        case 'T':
            output.append('T');
            output.append(data.getDesc());
            break;
        case 'D':
            output.append('D');
            output.append(data.getDesc());
            output.append('|');
            output.append(data.getDate());
            break;
        case 'E':
            output.append('E');
            output.append(data.getDesc());
            output.append('|');
            output.append(data.getDate());
            break;
        }
        return output.toString();
    }
}
