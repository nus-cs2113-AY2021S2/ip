import java.util.Scanner;

public class Duke {
    public static String inputValue = null;
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
       "____________________________________________________________\n");
        interaction();
    }

    public static void interaction(){
        do{
            Scanner input = new Scanner(System.in);
            inputValue = input.next();
            if(inputValue.equals("bye")){
                System.out.println("____________________________________________________________\n"+
                "Bye. Hope to see you again soon!"+"\n"+
                "____________________________________________________________\n");
            }else{
                System.out.println("____________________________________________________________\n"+
                inputValue+"\n"+
                "____________________________________________________________\n");
            }
           }while(!inputValue.equals("bye"));
    }
}
