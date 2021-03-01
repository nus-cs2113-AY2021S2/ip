

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class Duke implements Layout{
    public static TaskList taskList = new TaskList();

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
//                    List listObject = new List(taskList);
//                    listObject.toPrintList();
                    System.out.println(taskList.toStr());
                } else if (userInput.split(" ")[0].equals("done")) {
                    int temp = Integer.parseInt(userInput.split(" ")[1]);
                    Done doneObject = new Done(taskList.getList(), temp, taskList.get(temp - 1).commandDescription);
                    doneObject.toPrintDone();
                } else if (userInput.split(" ")[0].equals("todo")) {
                    if (userInput.split(" ")[1].equals("\n")) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    ToDo object = new ToDo(userInput);
                    object.toPrintToDo();
                    taskList.update(object);
                    taskList.save();
                    System.out.println("Now you have " + taskList.getSize()+" tasks in the list.");
                    displayLine();
                } else if (userInput.split(" ")[0].equals("deadline")) {
                    Deadline object = new Deadline(userInput);
                    object.toPrintDeadline();
                    taskList.update(object);
                    taskList.save();
                    System.out.println("Now you have " + taskList.getSize()+" tasks in the list.");
                    displayLine();
                } else if (userInput.split(" ")[0].equals("event")) {
                    Event object = new Event(userInput);
                    object.toPrintEvent();
                    taskList.update(object);
                    taskList.save();
                    System.out.println("Now you have " + taskList.getSize()+" tasks in the list.");
                    displayLine();
                }else if (userInput.split(" ")[0].equals("delete")) {
                    displayLine();
                    int temp = Integer.parseInt(userInput.split(" ")[1]);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(taskList.get(temp-1));
                    taskList.delete(temp - 1);
                    System.out.println("Now you have " + taskList.getSize()+" tasks in the list.");
                    displayLine();
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

