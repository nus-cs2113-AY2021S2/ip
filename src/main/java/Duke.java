import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n\n"
                +"____________________________________________________________\n";
        System.out.println(logo);
        Scanner stringScanner = new Scanner(System.in);
        String userQuery = stringScanner.nextLine();
        String userQueryReturn = "";
        while(!userQuery.equalsIgnoreCase("bye")){
            userQueryReturn = "____________________________________________________________\n"
                    + " " + userQuery + "\n"
                    + "____________________________________________________________\n";
            System.out.println(userQueryReturn);
            userQuery = stringScanner.nextLine();
        }
        String bye = "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n\n"
                +"____________________________________________________________\n";
        System.out.println(bye);
    }
}
