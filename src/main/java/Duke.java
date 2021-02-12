import javax.sound.midi.SysexMessage;
import java.util.Scanner;

public class Duke {

    static Task[] tasks = new Task[100];
    static int taskPosition = 0;
    static String description;
    static String by;
    static int indexOfBy;

    public static void storeTask(Task t) throws TodoException{
        if (!t.description.isEmpty()){
            tasks[taskPosition] = t;
            taskPosition++;
            System.out.println("Got it! I've added this task!");
            System.out.println(t.description);
            System.out.println(t.getStatusIcon() + " " + t.getDescription());
            System.out.println("Now you have " + countArray(tasks) + " tasks!");

        }
        else {
            throw new TodoException();
        }

    }

    public static int countArray(Task[] tasks){
        int counter = 0;
        for (Task task:tasks){
            if (task != null){
                counter++;
            }
        }
        return counter;
    }

    public static void markAsDone(int taskIndex){
        System.out.println("Nice! I've marked this task as done: ");
        tasks[taskIndex-1].isDone = true;
        listArray(tasks);
    }

    public static void listArray(Task[] tasks){
        int textNumber = 1;
        for(Task t:tasks){
            if(t != null){
                System.out.println(textNumber + "." + t.getStatusIcon() +" " + t.getDescription());
                textNumber++;
            }
        }
    }

    public static void main(String[] args) throws InvalidCommandException, InvalidDateException{
        System.out.println("Hello! I'm 343 Guilty Spark! You may call me Spark!");
        System.out.println("What can I do for you?");
        String text = "hi";

        while(!text.equalsIgnoreCase("Bye")){
            Scanner in = new Scanner(System.in);
            text = in.nextLine();
            //Task t = new Task(text);
            String[] arr = text.split(" "); //split command input into words
            String command = arr[0];

            switch (command){
            case "deadline":
            case "Deadline":
            case "DEADLINE":
                int indexOfBy = text.indexOf('/');
                description = text.substring(9,indexOfBy-1);
                by = text.substring(indexOfBy + 1);
                Deadline deadlineTask = new Deadline(description,by);
                try {
                    storeTask(deadlineTask);
                } catch (TodoException e) {
                    e.printStackTrace();
                }
                break;
            case "todo":
            case "Todo":
            case "TODO":

                try{
                    description = text.substring(5);
                    Todo todoTask = new Todo(description);
                    storeTask(todoTask);
                    break;
                } catch (TodoException e){
                    System.out.println("Todo cannot be empty");
                    break;
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("Todo list cannot be empty! Try again!");
                    break;
                }


            case "event":
            case "Event":
            case "EVENT":

                try {
                    if (!text.contains("/")){
                        throw new InvalidDateException();
                    }
                    indexOfBy = text.indexOf('/');

                    String description = text.substring(6,indexOfBy-1);
                    String by = text.substring(indexOfBy + 1);
                    Event eventTask = new Event(description,by);
                    storeTask(eventTask);
                } catch (TodoException e) {
                    System.out.println("Event description cannot be empty! Try again!");
                } catch (InvalidDateException e){
                    System.out.println("Event must have a date! Try again!");
                }
                break;
            case "List":
            case "list":
            case "LIST":
                listArray(tasks);
                break;
            case"Done":
            case "done":
            case "DONE":
                Integer taskIndex = Integer.parseInt(arr[1]);
                markAsDone(taskIndex);
                break;

            default:
                System.out.println("Invalid command. Only accepts Todo,List,Event or Deadline!");
            }


        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}
