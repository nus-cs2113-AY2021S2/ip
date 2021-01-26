import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("");
        boolean echoFlag = true;
        int n = 0;
        String[] listArray = new String[100];
        while(echoFlag) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            if(line.equals("bye")){
                echoFlag = false;
            }
            else if(line.equals("list")){
                for(int i=0;i<n;i++){
                    System.out.println((i+1)+". "+listArray[i]);
                }
            }
            else {
                listArray[n] = line;
                n++;
                System.out.println("added: " + line);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
