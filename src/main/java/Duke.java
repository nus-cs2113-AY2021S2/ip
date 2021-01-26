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
        greet();
        Scanner in = new Scanner(System.in);
        String input;
        input = in.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                list();
            }
            else if (checkDone(input)) {
                done(Integer.parseInt(input.trim().substring(5)));
            }
            else {
                add(input);
            }
            input = in.nextLine();
        }
        exit();
    }

    public static void greet() {
        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");
        end();
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!" + System.lineSeparator());
        end();
    }

    public static void end() {
        System.out.println("____________________________________________________________" + System.lineSeparator());
    }

    public static void echo(String input) {
        System.out.println(input);
        end();
    }

    private static Task[] list = new Task[100];

    private static int numOfTask = 0;

    public static void add(String input) {
        Task task = new Task(input);
        list[numOfTask] = task;
        numOfTask++;
        System.out.println("added: " + input);
        end();
    }

    public static void list() {
        int count = 0;
        while (count < numOfTask) {
            System.out.println(count + 1 + ": " + list[count]);
            count++;
        }
        end();
    }

    public static void done(int num) {
        list[num - 1].setAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(list[num - 1]);
        end();
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
