import java.util.*;
public class Duke {
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
        Task[] tasks = new Task[20];
        char ch1;
        char ch2;
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        int count1 = 0;
        do {
            int numerate = 0;
            if (in.equals("list")){
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

            else if (in.contains("done")){
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

            else{
                tasks[count1] = new Task();
                tasks[count1].setDescription(in);
                tasks[count1].setDone(false);
                System.out.println("--------------------------------------------");
                System.out.println("added: " + tasks[count1].getDescription());
                System.out.println("--------------------------------------------");
                count1++;
            }
            in = sc.nextLine();
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

    public void markAsDone(){
        isDone = true;
    }
    public String getDescription(){
        return this.description;
    }
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getPrintedLine(){
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    //...
}