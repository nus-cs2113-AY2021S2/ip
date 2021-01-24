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
        String[] listItems = new String[100];
        int itemCount = 0;
        while(!userQuery.equalsIgnoreCase("bye")){
            listItems[itemCount] = userQuery;
            if(!userQuery.equalsIgnoreCase("list")) {
                userQueryReturn = "____________________________________________________________\n"
                        + " added: " + userQuery + "\n"
                        + "____________________________________________________________\n";
                System.out.println(userQueryReturn);
                userQuery = stringScanner.nextLine();
            }else{
                System.out.println("____________________________________________________________");
                for(int i=0; i<itemCount; i++){
                    System.out.println(" "+String.valueOf(i+1)+". "+listItems[i]);
                }
                System.out.println("____________________________________________________________\n");
                userQuery = stringScanner.nextLine();
            }
            itemCount++;
        }
        String bye = "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n\n"
                +"____________________________________________________________\n";
        System.out.println(bye);
    }
}
