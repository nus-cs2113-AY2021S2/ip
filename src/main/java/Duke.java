import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        String inputCommand = sc.nextLine();
        String[] textList = new String[100];
        int listNumber = 0;

        while (!inputCommand.equals("bye")){
            if (inputCommand.equals("list")){
                for (int i = 0; i < listNumber; i++){
                    System.out.println(i + 1 + ". " + textList[i]);
                }
            } else if (inputCommand.equals("blah")){
                System.out.println(inputCommand);
            }else if (inputCommand.equals("bye")){
                break;
            }else {
                textList[listNumber] = inputCommand;
                listNumber++;
                System.out.println("added: "+ textList[listNumber-1]);
            }
            inputCommand = sc.nextLine();
        }
            System.out.println("Bye. Hope to see you again soon!");
    }
}
