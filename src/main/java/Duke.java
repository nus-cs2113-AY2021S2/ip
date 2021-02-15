
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {
    //public static String[] to_do_list= new String[100];
    //public static Task finish_goal = new Task("finish goal in list");
    public static Task t[] = new Task[100];

    public static void show_welcome_msg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
    }

    public static void show_list(int num) {
        System.out.println(" ____________________________________________________________\n" +
                "     Here are the tasks in your list:");
        for (int i = 0; i < num; i++) {
            System.out.println((i + 1) + ". " + "[" + t[i].getSymbol() + "]" + "[" + t[i].getStatusIcon() + "] " + t[i].getDescription());
        }
    }

    public static String userDone(String user_done) {
        switch (user_done) {
            case "todo":
                return "T";
            case "event":
                return "E";
            case "deadline":
                return "D";
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
        }

        return "";


    }

    public static boolean noInput(String input) {
        if (input.equals("")) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            return true;
        }
        return false;


    }

    public static void printTask(String taskKind, String substring, int num_of_goals, String ddl, String prep) {
        System.out.println("Got it. I've added this task: ");
        if (ddl == null) {
            System.out.println("[" + taskKind + "]" + "[" + " " + "]" + " " + substring);
        } else {
            System.out.println("[" + taskKind + "]" + "[" + " " + "]" + " " + substring + "(" + prep + ":" + ddl + ")");
        }

        System.out.println("Now you have " + num_of_goals + " task in your list!");
    }

    public static int totalNum() {
        int count = 0;
        for (int i = 0; i < t.length; i++) {
            if (t[i] != null) {
                count = count + 1;
            }

        }
        return count;
    }

    public static void delete(int index) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("[" + t[index].symbol + "]" + "[" + " " + "]" + " " + t[index].description + "(" + t[index].preposition + ":" + t[index].ddl + ")");
        for (int i = index; i < t.length - 1; i++) {
            t[i] = t[i + 1];
        }
        System.out.println("Now you have " + totalNum() + " task in your list!");
    }

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void loadFile() {
        try {
            printFileContents("/Users/chenlingcui/Desktop/CS2113/duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    //private static void appendToFile(String filePath, String textToAppend) throws IOException {
    //  FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
    //fw.write(textToAppend);
    //fw.close();
    //}

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

    public static void changeFile(int num) {

        try {
            writeToFile("/Users/chenlingcui/Desktop/CS2113/duke.txt", "[" + t[0].getSymbol() + "]" + "[" + t[0].getStatusIcon() + "] " + t[0].getDescription() + "(" + t[0].getPrep() + ":" + t[0].getDdl() + ")");
            appendToFile("/Users/chenlingcui/Desktop/CS2113/duke.txt", "\n");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        for (int i = 1; i < num; i++) {
            try {
                appendToFile("/Users/chenlingcui/Desktop/CS2113/duke.txt", "[" + t[i].getSymbol() + "]" + "[" + t[i].getStatusIcon() + "] " + t[i].getDescription() + "(" + t[i].getPrep() + ":" + t[i].getDdl() + ")");
                appendToFile("/Users/chenlingcui/Desktop/CS2113/duke.txt", "\n");
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }

    }


    public static void main(String[] args) {
        loadFile();
        show_welcome_msg();
        Scanner sc = new Scanner(System.in);
        String user_input = sc.nextLine();
        int num_of_goals = 0;

        while (!user_input.equals("bye")) {

            if (user_input.equals("list")) {
                show_list(totalNum());
            } else {
                int spacing = user_input.indexOf(" ");
                int slash = user_input.indexOf("/");
                //System.out.println(slash);
                String user_done = user_input.substring(0, spacing);
                //check whether user has done some tasks
                if (user_done.equals("done")) {
                    String number = user_input.substring(spacing + 1);
                    int number_converted = Integer.parseInt(number);
                    t[number_converted - 1].markAsDone();
                    System.out.println("____________________________________________________________\n" +
                            "     Nice! I've marked this task as done: ");
                    System.out.println("[" + t[number_converted - 1].getSymbol() + "]" + "[" + t[number_converted - 1].getStatusIcon() + "] " + t[number_converted - 1].getDescription() + "(" + t[number_converted - 1].getPrep() + ":" + t[number_converted - 1].getDdl() + ")");
                    System.out.println("____________________________________________________________");

                } else if (user_done.equals("delete")) {
                    String number = user_input.substring(spacing + 1);
                    int number_converted = Integer.parseInt(number);
                    delete(number_converted - 1);
                    num_of_goals = num_of_goals - 1;
                } else if (slash != -1) {

                    String taskKind = userDone(user_done);
                    if (!taskKind.equals("")) {
                        // System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        //break;

                        String taskName = user_input.substring(spacing + 1, slash);

                        if (!noInput(taskName)) {
                            String period = user_input.substring(slash + 1);
                            //System.out.println(period);
                            String prep = period.substring(0, period.indexOf(" "));
                            // System.out.println(prep);
                            String ddl = period.substring(period.indexOf(" ") + 1);
                            //System.out.println(ddl);

                            t[num_of_goals] = new Task(taskName);
                            t[num_of_goals].setSymbol(taskKind);
                            t[num_of_goals].setDdl(ddl);
                            t[num_of_goals].setPrep(prep);
                            num_of_goals++;

                            printTask(taskKind, taskName, num_of_goals, ddl, prep);
                        }
                    }


                } else {

                    String taskKind = userDone(user_done);
                    if (!taskKind.equals("")) {
                        //System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        //break;

                        String taskName = user_input.substring(spacing + 1);

                        if (!noInput(taskName)) {
                            t[num_of_goals] = new Task(taskName);
                            t[num_of_goals].setSymbol(taskKind);
                            //t[num_of_goals].setDdl(ddl);
                            num_of_goals++;

                            printTask(taskKind, taskName, num_of_goals, null, null);
                        }


                    }

                }

            }
            changeFile(num_of_goals);
            user_input = sc.nextLine();
        }


        System.out.println("____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }


}

