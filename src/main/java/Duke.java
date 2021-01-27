import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String line;
        String dashline = "____________________________________________________________\n";
        String IntroMsg = " Hello! I'm Duke\n" +
                        " What can I do for you?\n";
        String OutMsg = "Bye. Hope to see you again soon!\n";
        Scanner in = new Scanner(System.in);
        System.out.println(dashline + IntroMsg + dashline);
        line = in.nextLine();
        while(!line.equals("bye")){
            System.out.println(dashline + " " + line + "\n" + dashline);
            line = in.nextLine();
        }
        System.out.println(dashline + OutMsg + dashline);
    }
}
