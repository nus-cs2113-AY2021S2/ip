package Duke;

import Duke.Functions.*;
import Duke.Exceptions.DukeException;
import Duke.Task.*;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Duke {
    private static String LINE = "____________________________________________________________";
    public static String inputString;
    public static int taskCount = 0;
    public static List<Task> lists = new ArrayList<Task>();

    public static void main(String[] args) {
        greetings();
        readFile(lists);
        processInput();
        writeFile(lists);
        goodbye();
    }


    public static void processInput(){
        Scanner userInput = new Scanner(System.in);
        while(true) {
            inputString = userInput.nextLine().trim();
            String[] inputStringSplit = inputString.split(" ", 2);
            System.out.println(LINE);
            if (inputString.equalsIgnoreCase("bye")) {
                return;
            } else if (inputStringSplit[0].equalsIgnoreCase("done")) {
                if (inputStringSplit.length > 1) {
                    MarkAsDone.markAsDone(inputStringSplit[1]);
                } else {
                    DukeException.doneWithoutNo();
                }
            } else if (inputStringSplit[0].equalsIgnoreCase("delete")) {
                if (inputStringSplit.length > 1) {
                    Delete.deleteTask(inputStringSplit[1]);
                } else {
                    DukeException.deleteWithoutNo();
                }
            } else if (inputStringSplit[0].equalsIgnoreCase("list")) {
                printList(0, taskCount);
            } else if (AddToList.keywordCheck(inputStringSplit[0])) {
                if (inputStringSplit.length > 1) {
                    AddToList.AddToList(inputStringSplit[0], inputStringSplit[1]);
                } else {
                    DukeException.taskDescriptionEmpty();
                }
            } else {
                DukeException.illegalInput();
            }
        }
    }

    private static void readFile(List<Task> lists){
        try {
            File file = new File("src/main/java/Duke/Duke.txt");
            if (file.createNewFile()) {
                System.out.println("A new file has been created! ^_^");
            } else {
                System.out.println("Reading saved Task Lists ^_^");
                Scanner readingFile = new Scanner(file);
                while (readingFile.hasNextLine()) {
                    String line = readingFile.nextLine();
                    String[] parts = line.split("\\|", 4);
                    String type = parts[0];
                    String isDone = parts[1];
                    String task = parts[2];
                    if (type.equals("T")) {
                        Task taskInFile = new ToDoTask(task);
                        lists.add(taskInFile);
                        if (isDone.equals("true")) {
                            taskInFile.markAsDone();
                        }
                    } else if (type.equals("D")) {
                        String time = parts[3];
                        DeadlineTask taskInFile = new DeadlineTask(task, time);
                        lists.add(taskInFile);
                        if (isDone.equals("true")) {
                            taskInFile.markAsDone();
                        }
                    } else {
                        String time = parts[3];
                        EventTask taskInFile = new EventTask(task, time);
                        lists.add(taskInFile);
                        if (isDone.equals("true")) {
                            taskInFile.markAsDone();
                        }
                    }
                }
                taskCount = lists.size();
                printList(0, taskCount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(List<Task> lists){
        try {
            FileWriter writer = new FileWriter("src/main/java/Duke/Duke.txt",false);
            for (Task taskInList : lists) {
                if (taskInList instanceof ToDoTask) {
                    writer.write(taskInList.getTaskType() + "|" + taskInList.isDone() + "|" + taskInList.getTask().trim());
                } else {
                    writer.write(taskInList.getTaskType() + "|" + taskInList.isDone() + "|" + taskInList.getTask().trim() + "|" + taskInList.getTaskTime().trim());
                }
                writer.write("\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printList(int startIndex, int endIndex) {
        if (endIndex == 0) {
            System.out.println("List is empty :o\n" + LINE + "\n");
        } else {
            for(int i = startIndex; i < endIndex; ++i) {
                System.out.println(" " + (i + 1) + ": " + lists.get(i).toString());
            }
        }
    }

    public static void greetings(){
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println( LOGO + LINE + "\n" + " Hello! I'm Duke.Duke\n" + " What can I do for you?\n" + LINE + "\n" );
    }

    public static void goodbye(){
        System.out.println(" Bye! Hope to see you again soon!\n" + LINE );
    }
}
