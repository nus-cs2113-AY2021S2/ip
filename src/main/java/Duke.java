import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void printList(String[] list){
        for (String i : list){
            if(!i.isEmpty()) {
                System.out.println(i + "\n");
            }
        }
    }
    public static void main(String[] args) {
        int counter = 1;
        String task;
        String[] tasks =  new String[100];
        Arrays.fill(tasks, "");
        String line;
        String dashline = "____________________________________________________________\n";
        String IntroMsg = " Hello! I'm Duke\n" +
                        " What can I do for you?\n";
        String OutMsg = "Bye. Hope to see you again soon!\n";
        Scanner in = new Scanner(System.in);
        System.out.println(dashline + IntroMsg + dashline);
        line = in.nextLine();
        while(!line.equals("bye")){
            if(line.equals("list")){
                System.out.println(dashline);
                printList(tasks);
                System.out.println(dashline);
            }
            else {
                task = counter + ". " + line;
                tasks[counter] = task;
                counter++;
                System.out.println(dashline + " added: " + line + "\n" + dashline);
            }
            line = in.nextLine();
        }
        System.out.println(dashline + OutMsg + dashline);
    }
}
