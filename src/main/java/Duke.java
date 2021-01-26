import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n"+
                        "Hello! I'm Duke\n"+
                        "What can I do for you?\n"+
                        "____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        int i=0;
        String[] texts = new String[100];
        while(true){
            String command = sc.next();
            if(command.equals("bye")){
                System.out.println("____________________________________________________________\n"+
                        "Bye. Hope to see you again soon!\n"+
                        "____________________________________________________________");
                break;
            }else if(command.equals("list")){
                System.out.println("____________________________________________________________");
                for(int index=0; index<i;index++){
                    System.out.print(index+1);
                    System.out.println("." + texts[index]);
                }
                System.out.println("____________________________________________________________");
            }else{
                texts[i]=command;
                System.out.println("____________________________________________________________");
                System.out.println("added: "+texts[i]);
                i++;
                System.out.println("____________________________________________________________");
            }
        }


    }
}
