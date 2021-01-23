import java.util.Scanner;

public class Duke {

    public static String readPrompt(Scanner in){
        return in.nextLine();
    }

    public static void echoPrompt(String input){
        System.out.println(input);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String division_line = "____________________________________________________________\n";
        String say_hi = "Hello! I'm Ayanga, your personal task manager." + System.lineSeparator() +
                        "What can I do for you?\n";
        System.out.println(division_line + say_hi + division_line);
        Scanner in = new Scanner(System.in);
        String prompt = readPrompt(in);
        while (!prompt.equals("bye")) {
            //System.out.println(division_line);
            echoPrompt(prompt);
            System.out.println(division_line);
            prompt = readPrompt(in);
        }
        String say_bye = "Bye. Hope you have done your work next time I see you!\n";

        System.out.println(say_bye + division_line);
    }
}
