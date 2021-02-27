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

    public static int readFile(ArrayList<Task> list) {
        int taskCount = 0;
        try {
            File saveData = new File(Constants.FILENAME);
            if(saveData.createNewFile()) { //file is created
                System.out.println("data.txt created");
            } else { //file already exists
                Scanner reader = new Scanner(saveData);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    loadData(list, line);
                    taskCount++;
                }
                System.out.println("data loaded");
                System.out.println("tasks: " + taskCount);
            }
        } catch (IOException e) {
            System.out.print(Constants.LINE + Constants.GENERIC_ERROR_MESSAGE + Constants.LINE);
        }
        return taskCount;
    }

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
