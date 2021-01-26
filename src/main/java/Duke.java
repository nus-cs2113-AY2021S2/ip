import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String helloMessage = "Hello I'm Diuk! \nWhat would you like to do today?\n";
        String byeMessage = "Bye! Hit me up if you feel like being productive again ;)\n";
        String line = "____________________________________________________________\n";

        // Start - Greets user
        System.out.print(line);
        System.out.print(helloMessage);
        System.out.print(line);

        Scanner in = new Scanner(System.in);

        Task[] taskList = new Task[100];

        //Loop to receive response
        while (true){
            String input = in.nextLine();

            // BYE command
            if(input.equalsIgnoreCase("BYE")){
                break;
            }

            // LIST command
            if(input.equalsIgnoreCase("LIST")){
                int numbering = 1;

                // error handling - no jobs
                if(Task.taskCount == 0){
                    System.out.println("No tasks yet! What would you like to do today?\n");
                }
                else {
                    for (int i = 0; i < Task.taskCount; i++) {
                        System.out.print(numbering + ". ");
                        taskList[i].printTask();
                        numbering++;
                    }
                    System.out.println();
                }
            }

            // DONE command
            else if(input.toUpperCase().startsWith("DONE")){
                String[] word = input.split(" ");

                // handles invalid input
                if(word.length != 2){
                    System.out.println("Wrong format! Enter in the format: \"done [number]\"");
                    System.out.println("Make sure number is a valid integer! \n");
                    continue;
                }

                int jobNumber = Integer.parseInt(word[1]) - 1;

                if(jobNumber < Task.taskCount && jobNumber >= 0){
                    taskList[jobNumber].setDone(true);
                    System.out.print("Congrats! You've completed: \n   ");
                    taskList[jobNumber].printTask();
                    System.out.println();
                }
                else if(Task.taskCount == 0){
                    System.out.println("You don't have any tasks yet! Enter a task \n");
                }
                // smaller
                else if(jobNumber < 0){
                    System.out.println("Enter a valid job number. Use the list command to view your current tasks.\n");
                }

                // larger
                else {
                    System.out.println("You don't have that many jobs! Use the list command to view your current tasks.\n");
                }
            }

            // Adds new task
            else {
                // stores user command as job
                Task newTask = new Task(input);
                taskList[Task.taskCount] = newTask;
                Task.taskCount++;
                System.out.println("Added to list: " + newTask.getJob() + '\n');
            }
        }

        System.out.print(line);
        System.out.print(byeMessage);
        System.out.print(line);
    }

}
