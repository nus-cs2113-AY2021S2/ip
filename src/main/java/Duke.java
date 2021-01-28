import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String line;
        String []list= new String[100];
        Scanner in = new Scanner(System.in);
        int counter = 0;

        System.out.println("Hello! I'm Duke\nWhat can i do for you?\n");
        System.out.println("Hello! I'm Duke\nWhat can i do for you?\n");



        while(true) {

            line = in.nextLine();

            if(line.equals("bye")){
                break;
            }

            else if(line.equals("list")){
                int count=1;
                for(int i=0;i<counter;i++){
                    System.out.println((i+1)+". " +list[i]);
                }
            }
            else {
                list[counter++] = line;
                System.out.println("added: "+line);
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n");

    }
}
