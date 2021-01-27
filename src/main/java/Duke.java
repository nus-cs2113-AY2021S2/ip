import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println("Hello! I'm Duke"); //greets
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner in = new Scanner(System.in);

        //echoes user inputs:
        String input = "";
        input = in.nextLine();
        while (!input.equals("bye")) {
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
            input = in.nextLine();
        }

        //exits with "bye":
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!"); //exits
        System.out.println(line);

    }
}