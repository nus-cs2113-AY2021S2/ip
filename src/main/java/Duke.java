import java.util.Scanner;

public class Duke {
    public static class Tasks{
        private String description;
        private boolean isDone;
        public Tasks(String description){
            this.description = description;
            this.isDone =false;
        }
        public void setDescription(String description) {
            this.description = description;
        }

        public void setDone(boolean done) {
            isDone = done;
        }

        public String getDescription() {
            return this.description;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Tasks[] inputTasks = new Tasks[100];

        int counter = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        String input = in.nextLine();
        while(!input.equals("bye")){

            if(input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i< counter; i++) {
                    System.out.println(i+1+ ".[ ] " + inputTasks[i].getDescription());
                }
                System.out.println("____________________________________________________________");
            }
            else {
                Tasks temp = new Tasks(input);
                inputTasks[counter]=temp;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________");
            }
            counter++;
            input = in.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print("____________________________________________________________");
        System.out.print("\n");

    }
}
