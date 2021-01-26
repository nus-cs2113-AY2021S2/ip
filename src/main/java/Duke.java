import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        String inputCommand = sc.nextLine();
        String[] textList = new String[101];
        String[] doneList = new String[101];
        int listNumber = 1;
        String checkDone = null;

        while (!inputCommand.equals("bye")){
            if (inputCommand.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < listNumber; i++){
                    System.out.println(i + "." + "[" + doneList[i] + "] " + textList[i]);
                }
            } else if(inputCommand.equals("done")){

            }else if (inputCommand.equals("blah")){
                System.out.println(inputCommand);
            }else if (inputCommand.equals("bye")){
                break;
            }else {
                String trimmedCommand = inputCommand.trim();
                if (trimmedCommand.length() > 4) {
                    checkDone = trimmedCommand.substring(0, 4);
                }
                if("done".equals(checkDone) && (trimmedCommand.length() > 4)) {
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] " + inputCommand);
                    int doneIndex = Integer.parseInt(inputCommand.substring(5));
                    doneList[doneIndex] = "X";

                }else{
                    textList[listNumber] = inputCommand;
                    doneList[listNumber] = " ";
                    System.out.println("added: " + textList[listNumber]);
                    listNumber++;
                }
            }
            inputCommand = sc.nextLine();
        }
            System.out.println("Bye. Hope to see you again soon!");
    }
}
