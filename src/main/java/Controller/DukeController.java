package Controller;
import Tasks.*;
import java.util.ArrayList;
public class DukeController {

    public DukeController() {};
    public String[] processInput(String in) {
        String[] strings = new String[2];
        int index = in.indexOf(" ");
        String subString1 = in.substring(index+1);
        if (!subString1.contains("/")) {
            strings[0] = subString1;
            return strings;
        }
        else {
            int index1 = subString1.indexOf("/");
            String subString2 = subString1.substring(0, index1-1);
            String subString3 = subString1.substring(index1);
            int index2 = subString3.indexOf(" ");
            String subString4 = subString3.substring(index2+1);
            strings[0] = subString2;
            strings[1] = subString4;
            return strings;
        }
    }


    public int charNumber(String in) {
        char ch1, ch2;
        for (int i = 0; i <in.length(); i++){
            ch1 = in.charAt(i);
            int index = i;
            if (++index == in.length()){
                if(Character.isDigit(ch1)) {
                    return Character.getNumericValue(ch1);
                }
            }
            ch2 = in.charAt(index);
            if(Character.isDigit(ch1)&&Character.isDigit(ch2)) {
                return Character.getNumericValue(ch1)*10 + Character.getNumericValue(ch2);
            }
        }
        return 0;
    }

    public void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("--------------------------------------------");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("--------------------------------------------");
    }

    public void printTask(ArrayList<Task> tasks, int count) {
        System.out.println("--------------------------------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(count).getPrintedLine());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("--------------------------------------------");
    }

    public void printList(ArrayList<Task> tasks) {
        int count = 1;
        System.out.println("--------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (Task task: tasks) {
            System.out.println(count+ ". " + task.getPrintedLine());
            count++;
        }
        System.out.println("--------------------------------------------");
    }

    public void printDone(ArrayList<Task> tasks, String in) {
        int numerate;
        numerate = charNumber(in);
        try {
            if (numerate > tasks.size()) {
                throw new NullPointerException("There is no such event in your list");
            }
            System.out.println("--------------------------------------------");
            tasks.get(numerate-1).markAsDone();
            System.out.println(tasks.get(numerate-1).getPrintedLine());
            System.out.println("--------------------------------------------");
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------------");
        }
    }

    public void printTodo(ArrayList<Task> tasks, String in, String[] strings, int count1) {
        try{
            if (in.equals("todo")) {
                throw new DukeException("todo");
            }
            Task task = new ToDo(strings[0]);
            tasks.add(task);
            printTask(tasks, count1);
        }
        catch(DukeException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------------");
        }
    }

    public void printDeadline(ArrayList<Task> tasks, String in, String[] strings, int count1) {
        try{
            if (in.equals("deadline")) {
                throw new DukeException("deadline");
            }
            Task task = new Deadline(strings[0], strings[1]);
            tasks.add(task);
            printTask(tasks, count1);
        }
        catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------------");
        }
    }

    public void printEvent(ArrayList<Task> tasks, String in, String[] strings, int count1) {
        try{
            if (in.equals("event")) {
                throw new DukeException("event");
            }
            Task task = new Event(strings[0], strings[1]);
            tasks.add(task);
            printTask(tasks, count1);
        }
        catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------------");
        }
    }

    public void printDK(String in) {
        try {
            if (!in.contains("deadline") || !in.contains("event") || !in.contains("todo")) {
                throw new DukeException();
            }
        }
        catch (DukeException e) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println("--------------------------------------------");
        }
    }

    public void deleteTask(ArrayList<Task> tasks, String in){
        int numerate;
        numerate = charNumber(in);
        try {
            if (numerate > tasks.size()) {
                throw new NullPointerException("There is no such event in your list");
            }
            System.out.println("--------------------------------------------");
            System.out.println("Noted. I've removed this task: ");
            System.out.println(tasks.get(numerate-1).getPrintedLine());
            tasks.remove(numerate-1);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("--------------------------------------------");

        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------------");
        }
    }
}
