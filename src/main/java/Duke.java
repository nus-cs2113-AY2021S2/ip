import java.util.*;
public class Duke {

    public static String[] processInput(String in) {
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

    public static int listLength(Task[] tasks) {
        int length = 0;
        for (int i = 0; i<tasks.length; i++) {
            if (tasks[i] != null) {
                length++;
            }
        }
        return length;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("--------------------------------------------");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("--------------------------------------------");
        Task[] tasks = new Task[100];
        char ch1;
        char ch2;
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        String[] strings = new String[3];
        strings = processInput(in);
        int count1 = 0;
        do {
            int numerate = 0;
            if (in.equals("list")) {
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

            else if (in.contains("done")) {
                for (int i = 0; i <in.length(); i++){
                    ch1 = in.charAt(i);
                    int index = i;
                    if (++index == in.length()){
                        if(Character.isDigit(ch1)) {
                            numerate = Character.getNumericValue(ch1);
                            break;
                        }
                    }
                    ch2 = in.charAt(index);
                    if(Character.isDigit(ch1)&&Character.isDigit(ch2)) {
                        numerate = Character.getNumericValue(ch1)*10 + Character.getNumericValue(ch2);
                        break;
                    }
                }
                System.out.println("--------------------------------------------");
                System.out.println("Nice! I've marked this task as done: ");
                tasks[numerate-1].markAsDone();
                System.out.println(tasks[numerate-1].getPrintedLine());
                System.out.println("--------------------------------------------");
            }

            else if (in.contains("todo")) {
                tasks[count1] = new ToDo(strings[0]);
                System.out.println("--------------------------------------------");
                System.out.println("Got it. I've added this task: ");
                System.out.println(tasks[count1].getPrintedLine());
                System.out.println("Now you have " + listLength(tasks) + " tasks in the list.");
                System.out.println("--------------------------------------------");
                count1++;
            }

            else if (in.contains("deadline")) {
                tasks[count1] = new Deadline(strings[0], strings[1]);
                System.out.println("--------------------------------------------");
                System.out.println("Got it. I've added this task: ");
                System.out.println(tasks[count1].getPrintedLine());
                System.out.println("Now you have " + listLength(tasks) + " tasks in the list.");
                System.out.println("--------------------------------------------");
                count1++;
            }

            else if (in.contains("event")) {
                tasks[count1] = new Event(strings[0], strings[1]);
                System.out.println("--------------------------------------------");
                System.out.println("Got it. I've added this task: ");
                System.out.println(tasks[count1].getPrintedLine());
                System.out.println("Now you have " + listLength(tasks) + " tasks in the list.");
                System.out.println("--------------------------------------------");
                count1++;
            }

            else {
                tasks[count1] = new Task();
                tasks[count1].setDescription(in);
                tasks[count1].setDone(false);
                System.out.println("--------------------------------------------");
                System.out.println("added: " + tasks[count1].getDescription());
                System.out.println("--------------------------------------------");
                count1++;
            }
            in = sc.nextLine();
            strings = processInput(in);

        } while (!in.equals("bye"));
        System.out.println("--------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------------------");
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(){};

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void markAsDone() {
        isDone = true;
    }
    public String getDescription() {
        return this.description;
    }
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getPrintedLine(){
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

}

class Deadline extends Task {

    protected String by;

    public Deadline() {};

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getPrintedLine() {
        return "[D]" + super.getPrintedLine() + " (by: " + by + ")";
    }
}

class ToDo extends Task {

    public ToDo() {};
    public ToDo(String description) {
        super(description);
    }

    public String getPrintedLine() {
        return "[E]" + super.getPrintedLine();
    }
}

class Event extends Task {

    protected String at;

    public Event() {};

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getPrintedLine() {
        return "[D]" + super.getPrintedLine() + " (at: " + at + ")";
    }
}