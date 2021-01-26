import java.util.Scanner;

public class Duke {
    static final String lineDivider = "\t____________________________________________________________\n";
    static final String dukeGreeting = lineDivider + "\t Hello! I'm Duke\n\t What can I do for you?\n" + lineDivider;
    static final String dukeFarewell =  lineDivider + "\t Bye. Hope to see you again soon!\n" + lineDivider;

    static Scanner sc = new Scanner(System.in);

    public static void echos(){

        String userInput = sc.nextLine();

        while (!userInput.toLowerCase().equals("bye")){
//            String echos = lineDivider + "\t" + userInput.toLowerCase() + "\n" + lineDivider;
            String echos = String.format("%s\t %s\n%s",lineDivider,userInput.toLowerCase(),lineDivider);
            System.out.println(echos);
            userInput = sc.nextLine();
        }

        // Farewell procedure
        System.out.println(dukeFarewell);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);


        // Greetings
        System.out.println(dukeGreeting);

        // Do echo function
        echos();
    }

}
