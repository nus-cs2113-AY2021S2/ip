import java.util.Scanner;

public class Duke {
    public static void lineBreak(){
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Level 0: Greet
        lineBreak();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        lineBreak();
        //Level 2: Add, List
        Scanner in = new Scanner(System.in);
        String userInput;
        String[] userInputList = new String[100];
        int listCount = 0;
        while (true){
            userInput = in.nextLine();
            if (userInput.equals("list")){
                lineBreak();
                for (int i = 0; i < listCount; i++){
                    System.out.println(i+1 + ". " + userInputList[i]);
                }
                lineBreak();
                continue;
            }
            if (userInput.equals("bye")){
                break;
            }
            lineBreak();
            userInputList[listCount++] = userInput;
            System.out.println("added: " + userInput);
            lineBreak();
        }

        lineBreak();
        System.out.println("Bye. Hope to see you again soon!");
        lineBreak();
    }
}
