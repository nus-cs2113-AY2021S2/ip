import  java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        ArrayList<String> userInputList= new ArrayList<String>();

        while(!userInput.equals("bye")){
            if(userInput.equals("list")){
                for (int counter = 0; counter < userInputList.size(); counter++) {
                    System.out.println(counter+1 + ". " + userInputList.get(counter));
                }
            }
            else {
                System.out.println("added: " + userInput);
                userInputList.add(userInput);
            }
            userInput = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
