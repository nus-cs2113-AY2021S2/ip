package controller;
import model.*;
import view.Ui;
import exception.ErrorMessage;

public class Duke{
    private static int CAPCITY_OF_TASKS = 100;
    private static int taskNo = 0;
    private Ui ui;
    Task[] tasks = new Task[CAPCITY_OF_TASKS];

    public Duke(){
        ui = new Ui();
    }

    public void run() {

        String[] switchChoice;
        do {
            ui.printMenu();
            switchChoice = ui.getMenuInputChoice();
            try {
                switch (switchChoice[0]) {
                case "todo":
                    addToDo(switchChoice[1]);
                    break;
                case "deadline":
                    addDeadline(switchChoice[1]);
                    break;
                case "event":
                    addEvent(switchChoice[1]);
                    break;
                case "list":
                    printDescription();
                    break;
                case "done":
                    markDone(switchChoice[1]);
                    break;
                case "menu":
                    ui.printMenu();
                }
            }catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of a " + switchChoice[0] +  " cannot be empty.");
            }
        }while (!switchChoice[0].equals("bye"));
    }


    private void addDeadline(String input) {
        try{
            String[] inputSplit = input.split("/by");
            tasks[taskNo] = new Deadline(inputSplit[0], inputSplit[1]);
            tasks[taskNo].setTaskNo(taskNo);
            taskNo++;
        }catch(ArrayIndexOutOfBoundsException e){
            ui.printErrorMessage(ErrorMessage.ERROR_MISSING_BY_KEYWORD);
        }
    }

    private void addEvent(String input) {
        try{
            String[] inputSplit = input.split("/at");
            tasks[taskNo] = new Event(inputSplit[0], inputSplit[1]);
            tasks[taskNo].setTaskNo(taskNo);
            taskNo++;
        }catch(ArrayIndexOutOfBoundsException e){
            ui.printErrorMessage(ErrorMessage.ERROR_MISSING_AT_KEYWORD);
        }
    }

    private void addToDo(String input) {
        tasks[taskNo] = new Todo(input);
        tasks[taskNo].setTaskNo(taskNo);
        taskNo++;
    }

    private void printDescription(){
        System.out.println("Select this to print description");
        for (int i = 0; i < taskNo; i++) {
            System.out.println((tasks[i].getTaskNo() + 1) + ". " + tasks[i].toString());
        }
        System.out.println("You have " + (taskNo) + " task(s) in the list." );

    }

    private void markDone(String input){
        int newValue = Integer.parseInt(input);
        tasks[newValue - 1].setDone(true);
        System.out.println("model.Task sucessfully marked as done!");
    }
}