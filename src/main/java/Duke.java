import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.printf("Hello! I'm Duke\n" +
                "What can I do for you?\n\n");
t
        System.out.printf("--------User Menu--------\n" +
                "list: list current tasks and completion status\n" +
                "done: Mark a task as completed\n" +
                "bye: Exit\n" +
                "any other words: The word will be recorded as a task\n");

        Task[] tasks = new Task[100];
        int currentTaskLength = 0;

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("bye")){
            if(input.equals("list")){
                for(int i=0; i<currentTaskLength; i++){
                    System.out.println(i+1 + ".["
                            + tasks[i].getStatusIcon() + "] "
                            + tasks[i].getDescription());
                }
                input = sc.nextLine();
                continue;
            } else if(input.length() > 5){
                String firstFiveChars = input.substring(0, 5);
                String sixthToLastChars = input.substring(5);
                if(firstFiveChars.equals("done ") && isInteger(sixthToLastChars)){
                    int taskIndex = Integer.parseInt(sixthToLastChars) - 1;
                    if(taskIndex < currentTaskLength){
                        tasks[taskIndex].setIsDone(true);
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println("["
                                + tasks[taskIndex].getStatusIcon() + "] "
                                + tasks[taskIndex].getDescription());
                        input = sc.nextLine();
                        continue;
                    }
                }
            }

            tasks[currentTaskLength] = new Task(input);
            System.out.println("added: " + tasks[currentTaskLength].getDescription());
            currentTaskLength++;
            input = sc.nextLine();

        }
        System.out.printf("Bye. Hope to see you again soon!\n");
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
}