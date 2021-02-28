

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;



public class Duke implements Layout{
    public static ArrayList<Tasks> taskList = new ArrayList<>();

    public static void displayInitialMessage(){
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
    }

    public static void displayLine(){
        System.out.println("____________________________________________________________");
    }

    public static void displayGoodByeMessage(){
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }


    public static void main(String[] args) {
        displayInitialMessage();
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            try {
                if (userInput.equals("list")) {
                    List listObject = new List(taskList);
                    listObject.toPrintList();
                } else if (userInput.split(" ")[0].equals("done")) {
                    int temp = Integer.parseInt(userInput.split(" ")[1]);
                    Done doneObject = new Done(taskList, temp, taskList.get(temp - 1).commandDescription);
                    doneObject.toPrintDone();
                } else if (userInput.split(" ")[0].equals("todo")) {
                    if (userInput.split(" ")[1].equals("\n")) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    ToDo object = new ToDo(taskList, userInput);
                    object.toPrintToDo();
                } else if (userInput.split(" ")[0].equals("deadline")) {
                    Deadline object = new Deadline(taskList, userInput);
                    object.toPrintDeadline();
                } else if (userInput.split(" ")[0].equals("event")) {
                    Event object = new Event(taskList, userInput);
                    object.toPrintEvent();
                } else if (userInput.split(" ")[0].equals("delete")) {
                    Delete object = new Delete(taskList,Integer.parseInt(userInput.split(" ")[1]), userInput);
                    object.toPrintDelete();
                    object.deleteTask();
                }
                else {
                    throw new EmptyStackException();
                }
            }
            catch(ArrayIndexOutOfBoundsException e){
                displayLine();
                System.out.println("☹ OOPS!!! The description of the command cannot be empty ");
                displayLine();
            }
            catch(EmptyStackException e){
                displayLine();
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                displayLine();
            }
            catch (StringIndexOutOfBoundsException e ){
                displayLine();
                System.out.println("☹ OOPS!!! The description of the command should be in the right format ");
                displayLine();
            }

            userInput = in.nextLine();
        }
        displayGoodByeMessage();

    }
}

