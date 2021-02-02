import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String line;
        String []word = new String[2];
        String []list= new String[100];
        Scanner in = new Scanner(System.in);
        int counter = 0;

        System.out.println("Hello! I'm Duke\nWhat can i do for you?\n");

        while(true) {

            line = in.nextLine();

            if(line.equals("bye")){
                break;
            }

            else if(line.equals("list")){
                //print list
                new Task().printList();
            }
            else if(line.contains("done")){
                //spilt the word to get the number
                String [] words = line.split(" ");
                //get the number
                int index = Integer.parseInt(words[words.length-1]);
                new Task().MarkAsDone(index-1);
            }
            else {
                //add task
                new Task().addTask(line);
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n");

    }
}
