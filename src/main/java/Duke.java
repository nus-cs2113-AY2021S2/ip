import java.security.spec.RSAOtherPrimeInfo;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Greet();
        Scanner in = new Scanner(System.in);
        String input;
        input = in.nextLine();
        String[] splitInput = input.split(" ");
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                List();
            }
            else if (input.length() < 5 ) {
                Add(input);
            }
            else if (checkDone(input)) {
                done(Integer.parseInt(input.trim().substring(5)));
            }
            else {
                Add(input);
            }
            input = in.nextLine();
        }
        Exit();
    }

    public static void Greet() {
        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");
        End();
    }

    public static void Exit() {
        System.out.println("Bye. Hope to see you again soon!" + System.lineSeparator());
        End();
    }

    public static void End() {
        System.out.println("____________________________________________________________" + System.lineSeparator());
    }

    public static void Echo(String input) {
        System.out.println(input);
        End();
    }

    private static Task[] list = new Task[100];

    private static int numOfTask = 0;

    public static void Add(String input) {
        Task task = new Task(input);
        list[numOfTask] = task;
        numOfTask++;
        System.out.println("added: " + input);
        End();
    }

    public static void List() {
        int count = 0;
        while (count < numOfTask) {
            System.out.println(count+1 + ": " + list[count]);
            count++;
        }
        End();
    }

    public static void done(int num) {
        list[num-1].setAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(list[num-1]);
        End();
    }

    public static boolean checkDone (String input) {
        String [] inputArray = input.split(" ");
        if(inputArray.length != 2){
            return false;
        }
        else {
            try{
                Integer.valueOf(inputArray[1]);
                return true;
            }
            catch (Exception e){
                return false;
            }
        }
    }
}
