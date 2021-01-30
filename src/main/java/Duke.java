import java.util.Scanner;

public class Duke {
    public static String inputValue[] = new String [100];
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
        int listIndex = 0;
        do{
            Scanner input = new Scanner(System.in);
            inputValue[listIndex] = input.next();
            if(inputValue[listIndex].equals("bye")){
                System.out.println("____________________________________________________________\n"+
                "Bye. Hope to see you again soon!"+"\n"+
                "____________________________________________________________\n");
            }else if(inputValue[listIndex].equals("list")){
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i<listIndex; i++){
                    int index = i+1;
                    System.out.println(index +". "+ inputValue[i]);
                }
                System.out.println("____________________________________________________________\n");
            }else{
                System.out.println("____________________________________________________________\n"+
                "added: "+inputValue[listIndex]+"\n"+
                "____________________________________________________________\n");
                listIndex+=1;
                inputValue[listIndex]=inputValue[listIndex-1];
            }
           }while(!inputValue[listIndex].equals("bye"));
    }
}
