import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("--------------------------------------------");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("--------------------------------------------");
        ArrayList<String> added = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        do {
            if (in.equals("list")){
                int count = 1;
                System.out.println("--------------------------------------------");
                for (String i : added){
                    System.out.println(count+ ". " + i);
                    count++;
                }
                System.out.println("--------------------------------------------");
            }
            else{
                added.add(in);
                System.out.println("--------------------------------------------");
                System.out.println("added: " + in);
                System.out.println("--------------------------------------------");}
            in = sc.nextLine();
        } while (!in.equals("bye"));
        System.out.println("--------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------------------");
    }
}
