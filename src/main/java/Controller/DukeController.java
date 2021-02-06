package Controller;
import Tasks.*;
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

    public int listLength(Task[] tasks) {
        int length = 0;
        for (int i = 0; i<tasks.length; i++) {
            if (tasks[i] != null) {
                length++;
            }
        }
        return length;
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

    public void printTask(Task[] tasks, int count) {
        System.out.println("--------------------------------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks[count].getPrintedLine());
        System.out.println("Now you have " + listLength(tasks) + " tasks in the list.");
        System.out.println("--------------------------------------------");
    }

    public void printList(Task[] tasks) {
        int count = 1;
        System.out.println("--------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i< tasks.length; i++){
            if (tasks[i] != null){
                System.out.println(count+ ". " + tasks[i].getPrintedLine());
                count++;}
        }
        System.out.println("--------------------------------------------");
    }

    public void printDone(Task[] tasks, String in) {
        int numerate;
        numerate = charNumber(in);
        try {
            if (numerate > listLength(tasks)) {
                throw new NullPointerException("There is no such event in your list");
            }
            System.out.println("--------------------------------------------");
            tasks[numerate-1].markAsDone();
            System.out.println(tasks[numerate-1].getPrintedLine());
            System.out.println("--------------------------------------------");
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------------");
        }
    }

    public void printTodo(Task[] tasks, String in, String[] strings, int count1) {
        try{
            if (in.equals("todo")) {
                throw new DukeException("todo");
            }
            tasks[count1] = new ToDo(strings[0]);
            printTask(tasks, count1);
        }
        catch(DukeException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------------");
        }
    }

    public void printDeadline(Task[] tasks, String in, String[] strings, int count1) {
        try{
            if (in.equals("deadline")) {
                throw new DukeException("deadline");
            }
            tasks[count1] = new Deadline(strings[0], strings[1]);
            printTask(tasks, count1);
        }
        catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------------");
        }
    }

    public void printEvent(Task[] tasks, String in, String[] strings, int count1) {
        try{
            if (in.equals("event")) {
                throw new DukeException("event");
            }
            tasks[count1] = new Event(strings[0], strings[1]);
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
}
