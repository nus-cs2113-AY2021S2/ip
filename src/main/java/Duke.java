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
        String[] list = new String[100];
        int itemCount = 0;
        input = in.nextLine();
        while (!input.equals("bye")) {

            System.out.println(line);
            if (input.equals("list")){
                for (int i = 0; i < itemCount; i++){
                    System.out.println(i+1 + ". " + list[i]);
                }
            }
            else {
                System.out.println("added: " + input); //output based on user input
                list[itemCount] = input; //stores user inputs into list
                itemCount++;
            }
            System.out.println(line);
            input = in.nextLine();
        }

        //exits with "bye":
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!"); //exits
        System.out.println(line);

    }
}