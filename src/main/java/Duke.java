import java.util.Scanner;

import commands.Task;
import commands.Todo;
import commands.Deadline;
import commands.Event;
import exceptions.DukeException;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    public static final int maxTasks = 100;
    private static final String border = "    ――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――";

    public static final ArrayList<Task> tasks = new ArrayList<Task>();
    public static int n = 0;

    public static void main(String[] args) {
        try {
            loadFile();
        } catch (FileNotFoundException e) {
            System.out.println("No saved file found");
        }
        welcomeMessage();
        if (!tasks.isEmpty()) {
            System.out.println(border);
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < n; i++) {
                System.out.println("    " + (i + 1) + ". " + tasks.get(i));
            }
            System.out.println(" ");
            System.out.println(border);
        }
        Scanner in = new Scanner(System.in);
        boolean isOn = true;
        int numOfCompletedTasks = 0;
        int numOfTasks = 0;
        while (isOn && in.hasNextLine()) {
            String line = in.nextLine();
            String[] words = line.split(" ");
            try {
                if (line.equals("bye")) {
                    try {
                        saveFile();
                    } catch (IOException e) {
                        System.out.println("Unable to save the current list as a text file");
                    }
                    System.out.println(border);
                    System.out.println("    Bye. Hope to see you again soon!");
                    System.out.println(" ");
                    System.out.println(border);
                    isOn = false;
                } else if (words[0].equals("todo")) {
                    String description = "";
                    try {
                        int wordsCount = words.length;
                        if (words.length == 1) {
                            throw new DukeException("missing_parameter");
                        }
                        for (String word : words) {
                            wordsCount--;
                            if (word.equals("todo")) {
                                continue;
                            } else if (wordsCount == 0) {
                                description = description + word;
                            } else {
                                description = description + word + " ";
                            }
                        }
                        Todo todo = new Todo(description);
                        tasks.add(todo);
                        System.out.println(border);
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("      " + tasks.get(n));
                        n++;
                        System.out.println("    Now you have " + n + " tasks in the list.");
                        System.out.println(" ");
                        System.out.println(border);
                        try {
                            saveFile();
                        } catch (IOException e) {
                            System.out.println("Unable to save the current list as a text file");
                        }
                    } catch (DukeException e) {
                        System.out.println(border);
                        System.out.println("    OOPS!! The description of a todo cannot be empty.");
                        System.out.println(border);
                    }
                } else if (words[0].equals("deadline")) {
                    String description = "";
                    String by = "";
                    try {
                        boolean byFlag = false;
                        int wordsCount = words.length;
                        if (wordsCount == 1) {
                            throw new DukeException("missing_parameter");
                        }
                        for (String word : words) {
                            wordsCount--;
                            if (word.equals("/by")) {
                                byFlag = true;
                                continue;
                            }
                            if (word.equals("deadline")) {
                                continue;
                            }
                            if (byFlag && wordsCount == 0) {
                                by = by + word;
                            } else if (byFlag) {
                                by = by + word + " ";
                            } else {
                                description = description + word + " ";
                            }
                        }
                        Deadline deadline = new Deadline(description, by);
                        tasks.add(deadline);
                        System.out.println(border);
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("      " + tasks.get(n));
                        n++;
                        System.out.println("    Now you have " + n + " tasks in the list.");
                        System.out.println(" ");
                        System.out.println(border);
                        try {
                            saveFile();
                        } catch (IOException e) {
                            System.out.println("Unable to save the current list as a text file");
                        }
                    } catch (DukeException e) {
                        System.out.println(border);
                        System.out.println("    OOPS!! The description of the deadline cannot be empty.");
                        System.out.println(border);
                    }

                } else if (words[0].equals("event")) {
                    String description = "";
                    String at = "";
                    try {
                        boolean atFlag = false;
                        int wordsCount = words.length;
                        if (wordsCount == 1) {
                            throw new DukeException("missing_parameter");
                        }
                        for (String word : words) {
                            wordsCount--;
                            if (word.equals("/at")) {
                                atFlag = true;
                                continue;
                            }
                            if (word.equals("event")) {
                                continue;
                            }
                            if (atFlag && wordsCount == 0) {
                                at = at + word;
                            } else if (atFlag) {
                                at = at + word + " ";
                            } else {
                                description = description + word + " ";
                            }
                        }
                        Event event = new Event(description, at);
                        tasks.add(event);
                        System.out.println(border);
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("      " + tasks.get(n));
                        n++;
                        System.out.println("    Now you have " + n + " tasks in the list.");
                        System.out.println(" ");
                        System.out.println(border);
                        try {
                            saveFile();
                        } catch (IOException e) {
                            System.out.println("Unable to save the current list as a text file");
                        }
                    } catch (DukeException e) {
                        System.out.println(border);
                        System.out.println("    OOPS!! The description of the deadline cannot be empty.");
                        System.out.println(border);
                    }

                } else if (words[0].equals("done")) {
                    int taskNum = Integer.parseInt(words[1]) - 1;
                    tasks.get(taskNum).markAsDone();
                    System.out.println(border);
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("      " + tasks.get(taskNum));
                    System.out.println(" ");
                    System.out.println(border);
                    try {
                        saveFile();
                    } catch (IOException e) {
                        System.out.println("Unable to save the current list as a text file");
                    }
                } else if (line.equals("list")) {
                    System.out.println(border);
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < n; i++) {
                        System.out.println("    " + (i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println(" ");
                    System.out.println(border);
                } else if (words[0].equals("delete")) {
                    int taskNum = Integer.parseInt(words[1]) - 1;
                    if (taskNum < n && taskNum >= 0) {
                        System.out.println(border);
                        System.out.println("    Noted. I've removed this task:");
                        System.out.println("      " + tasks.get(taskNum));
                        System.out.println(" ");
                        System.out.println(border);
                        tasks.remove(taskNum);
                        n--;
                    } else {
                        System.out.println(border);
                        System.out.println("    Index out of range");
                        System.out.println(" ");
                        System.out.println(border);
                    }
                    try {
                        saveFile();
                    } catch (IOException e) {
                        System.out.println("Unable to save the current list as a text file");
                    }
                } else {
                    throw new DukeException("unrecognized_input");
                }
            } catch (DukeException e) {
                System.out.println(border);
                System.out.println("    OOPS!! I'm sorry, but I don't know what that means.");
                System.out.println(border);
            }
        }
    }

    public static void saveFile() throws IOException {
        File filePath = new File("duke.txt");
        if (!filePath.exists()) {
            if (filePath.createNewFile()) {
                throw new IOException();
            }
        }
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i < n; i++) {
            String task = tasks.get(i).toString() + "\n";
            fileWriter.write(task);
        }
        fileWriter.close();
    }

    public static void loadFile() throws FileNotFoundException {
        File filePath = new File("duke.txt");
        if (!filePath.exists()) {
            throw new FileNotFoundException();
        }
        Scanner s = new Scanner(filePath);
        if (filePath.length() != 0) {
            while (s.hasNext()) {
                String currentLine = s.nextLine();
                String[] words = currentLine.split(" ");
                if (words[0].equals("[T]")) {
                    String description = "";
                    int startingIndex = 2;
                    if (words[1].equals("[")) {
                        startingIndex = 3;
                    }
                    for (int i = startingIndex; i < words.length; i++) {
                        description = description + words[i] + " ";
                    }
                    Todo todo = new Todo(description);
                    tasks.add(todo);
                    if (words[1].equals("[X]")) {
                        tasks.get(n).markAsDone();
                    }
                    n++;
                } else if (words[0].equals("[D]")) {
                    String description = "";
                    String by = "";
                    boolean byFlag = false;
                    int startingIndex = 2;
                    if (words[1].equals("[")) {
                        startingIndex = 3;
                    }
                    for (int i = startingIndex; i < words.length; i++) {
                        if (words[i].equals("(by:")) {
                            byFlag = true;
                        } else if (!byFlag) {
                            description = description + words[i] + " ";
                        } else {
                            if(i== words.length-1){
                                by = by + words[i];
                            }else {
                                by = by + words[i] + " ";
                            }
                        }
                    }
                    by = by.replace(")", "");
                    Deadline deadline = new Deadline(description, by);
                    tasks.add(deadline);
                    if (words[1].equals("[X]")) {
                        tasks.get(n).markAsDone();
                    }
                    n++;
                } else if (words[0].equals("[E]")) {
                    String description = "";
                    String at = "";
                    boolean atFlag = false;
                    int startingIndex = 2;
                    if (words[1].equals("[")) {
                        startingIndex = 3;
                    }
                    for (int i = startingIndex; i < words.length; i++) {
                        if (words[i].equals("(at:")) {
                            atFlag = true;
                        } else if (!atFlag) {
                            description = description + words[i] + " ";
                        } else {
                            if (i == words.length - 1) {
                                at = at + words[i];
                            } else {
                                at = at + words[i] + " ";
                            }
                        }
                    }
                    at = at.replace(")", "");
                    Event event = new Event(description, at);
                    tasks.add(event);
                    if (words[1].equals("[X]")) {
                        tasks.get(n).markAsDone();
                    }
                    n++;
                }
            }
        }
    }

    private static void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(" ");
        System.out.println("――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
    }
}
