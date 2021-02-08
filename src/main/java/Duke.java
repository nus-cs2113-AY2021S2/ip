import exceptions.IllegalCommandException;
import exceptions.IllegalListException;
import exceptions.IllegalTaskException;
import list.Deadline;
import list.Event;
import list.List;
import list.Todo;
import java.util.Scanner;

public class Duke {

    public static final int MAX_LIST_SIZE = 100;
    public static final String CHAT_BOT_NAME = "Arthur";

    public static void main(String[] args) {
        greet();
        processCommands();
    }

    private static void processCommands() {
        String line;
        List dukeList = new List(CHAT_BOT_NAME);
        Todo todoList = new Todo(MAX_LIST_SIZE);
        Deadline deadLineList = new Deadline(MAX_LIST_SIZE);
        Event eventLists = new Event(MAX_LIST_SIZE);

        Scanner in = new Scanner(System.in);

        boolean hasToContinue = true;
        dukeList.printHelp();
        while (hasToContinue) {
            line = in.nextLine();
            hasToContinue = selectCommand(dukeList, line, hasToContinue, todoList, deadLineList, eventLists);
        }

    }

    private static boolean selectCommand(List dukeList, String line, boolean hasToContinue, Todo todoList,
                                         Deadline deadLineList, Event eventLists) {
        switch (line) {
        case "bye":
            exit(dukeList, todoList, deadLineList, eventLists);
            hasToContinue = false;
            break;
        case "list":
            printAllLists(dukeList, todoList, deadLineList, eventLists);
            break;
        case "help":
            dukeList.printHelp();
            break;

        default:
            handleAmendList(dukeList, line, todoList, deadLineList, eventLists);
            break;
        }
        return hasToContinue;
    }

    private static void handleAmendList(List dukeList, String line, Todo todoList, Deadline deadLineList, Event eventLists) {
        try {
            amendList(dukeList, line, todoList, deadLineList, eventLists);
        } catch (IllegalCommandException e) {
            dukeList.printCommandDoesNotExist();
        } catch (IllegalListException e) {
            dukeList.printWrongListName();
            dukeList.printDottedLines();

        }
    }

    private static void printAllLists(List dukeList, Todo todoList, Deadline deadLineList, Event eventLists) {
        if (todoList.getTasksCounter() > 0) {
            todoList.printList();
        }
        if (deadLineList.getTasksCounter() > 0) {
            deadLineList.printList();
        }
        if (eventLists.getTasksCounter() > 0) {
            eventLists.printList();
        }
        if (dukeList.getTasksCounter() == 0) {
            dukeList.printEmptyList();
            dukeList.printDottedLines();
            return;
        }
        printAllTasksCounter(dukeList, todoList, deadLineList, eventLists);
        dukeList.printDottedLines();
    }

    private static void amendList(List dukeList, String line, Todo todoList, Deadline deadLineList, Event eventsList)
            throws IllegalCommandException, IllegalListException {
        String[] sentence = line.split(" ");
        if (sentence.length < 2) {
            throw new IllegalCommandException();
        }
        switch (sentence[0]) {
        case "done":
            try {
                markTaskAsDone(dukeList, sentence, todoList, deadLineList, eventsList);
            } catch (IllegalCommandException e) {
                throw new IllegalCommandException();
            } catch (IllegalListException e) {
                throw new IllegalListException();
            }
            return;

        case "todo": {
            addTaskInTodoList(line, todoList);
            break;
        }
        case "deadline": {
            addTaskInTodoOrDeadlineList(line, deadLineList);
            break;
        }
        case "event": {
            addTaskInTodoOrDeadlineList(line, eventsList);
            break;
        }
        default:
            throw new IllegalCommandException();
        }
        dukeList.incrementTaskCounter();
    }

    private static void addTaskInTodoOrDeadlineList(String line, Deadline deadLineList) {
        String[] commandWords = (line.split(" ", 2));
        String description = commandWords[1];
        deadLineList.addNewTask(description);
    }

    private static void addTaskInTodoList(String line, Todo todoList) {
        String[] commandWords = (line.split(" ", 2));
        String description = commandWords[1];
        todoList.addNewTask(description);
    }

    private static void markTaskAsDone(List dukeList, String[] sentence, Todo todoList, Deadline deadLineList,
                                       Event eventList) throws IllegalCommandException, IllegalListException {
        int index;
        if (sentence.length == 2) {
            throw new IllegalCommandException();
        }
        String listToChooseFrom = sentence[1];
        switch (listToChooseFrom) {
        case "todo":
            index = getIndexFromCommand(sentence[2]);
            try {
                todoList.markAsDone(index);
            } catch (IllegalTaskException e) {
                System.out.println(todoList.getInvalidTaskPhrase());
            }
            break;
        case "deadline":
            index = getIndexFromCommand(sentence[2]);
            try {
                deadLineList.markAsDone(index);
            } catch (IllegalTaskException e) {
                System.out.println(deadLineList.getInvalidTaskPhrase());
            }

            break;
        case "event":
            index = getIndexFromCommand(sentence[2]);
            try {
                eventList.markAsDone(index);
            } catch (IllegalTaskException e) {
                System.out.println(eventList.getInvalidTaskPhrase());
            }
            break;
        default:
            throw new IllegalListException();
        }
        if (dukeList.getTasksCounter() > 0) {
            printAllTasksCounter(dukeList, todoList, deadLineList, eventList);
            dukeList.printDottedLines();
        }


    }

    private static void printAllTasksCounter(List dukeList, Todo todoList, Deadline deadLineList, Event eventLists) {
        int totalNumberOfTasksRemaining = todoList.getNumberTasksLeft()
                + deadLineList.getNumberTasksLeft() + eventLists.getNumberTasksLeft();


        if (totalNumberOfTasksRemaining == 0) {
            dukeList.printCompletedTasksInAllLists();
        } else if (totalNumberOfTasksRemaining > 0) {
            if (totalNumberOfTasksRemaining == todoList.getTasksCounter()
                    + deadLineList.getTasksCounter() + eventLists.getTasksCounter()) {
                dukeList.printNoTasksDoneInAllList();
            } else {
                dukeList.printSomeTasksRemaining(totalNumberOfTasksRemaining);

            }
        }
    }

    public static int getIndexFromCommand(String index) {
        try {
            return Integer.parseInt(index) - 1;
        } catch (NumberFormatException nfe) {
            return -1;
        }

    }


    public static void greet() {
        String logo = getLogo();
        System.out.println("Ssshhhhhh!!!!!" + System.lineSeparator() + logo);
        String greetings = "____________________________________________________________________________________"
                + System.lineSeparator()
                + "Hello Crewmate! I'm Arthur, ( ͡°͜ ʖ ͡°)" + System.lineSeparator()
                + "Please assign me my tasks to complete!" + System.lineSeparator()
                + "____________________________________________________________________________________"
                + System.lineSeparator();
        System.out.println(greetings);
    }

    private static String getLogo() {
        return "             ⣠⣤⣤⣤⣤⣤⣶⣦⣤⣄⡀" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⠀ ⢀⣴⣿⡿⠛⠉⠙⠛⠛⠛⠛⠻⢿⣿⣷⣤⡀" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⠀ ⣼⣿⠋⠀⠀⠀⠀⠀⠀⠀  ⢀⣀⣀⠈⢻⣿⣿⡄" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀ ⣸⣿⡏⠀⠀⠀ ⣠⣶⣾⣿⣿⣿⠿⠿⠿⢿⣿⣿⣿⣄" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀ ⣿⣿⠁⠀⠀ ⢰⣿⣿⣯⠁⠀⠀⠀⠀⠀⠀⠀ ⠈⠙⢿⣷⡄" + System.lineSeparator()
                + " ⠀⠀⣀⣤⣴⣶⣶⣿⡟⠀⠀⠀ ⢸⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⣿⣷" + System.lineSeparator()
                + " ⠀⢰⣿⡟⠋⠉⣹⣿⡇⠀⠀⠀ ⠘⣿⣿⣿⣿⣷⣦⣤⣤⣤⣶⣶⣶⣶⣿⣿⣿" + System.lineSeparator()
                + " ⠀⢸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀ ⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃" + System.lineSeparator()
                + " ⠀⣸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀ ⠉⠻⠿⣿⣿⣿⣿⡿⠿⠿⠛⢻⣿⡇" + System.lineSeparator()
                + " ⠀⣿⣿⠁⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⢸⣿⣧" + System.lineSeparator()
                + " ⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⢸⣿⣿" + System.lineSeparator()
                + " ⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⢸⣿⣿" + System.lineSeparator()
                + " ⠀⢿⣿⡆⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⢸⣿⡇" + System.lineSeparator()
                + " ⠀⠸⣿⣧⡀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⣿⣿⠃" + System.lineSeparator()
                + " ⠀⠀⠛⢿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⣰⣿⣿⣷⣶⣶⣶⣶⠶⠀⢠⣿⣿" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⣽⣿⡏⠁⠀⠀  ⢸⣿⡇" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⢹⣿⡆⠀⠀⠀  ⣸⣿⠇" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⢿⣿⣦⣄⣀⣠⣴⣿⣿⠁⠀⠈⠻⣿⣿⣿⣿⡿⠏" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⠈⠛⠻⠿⠿⠿⠿⠋⠁" + System.lineSeparator();

    }


    public static void exit(List dukeList, Todo todoList, Deadline deadLineList, Event eventLists) {

        boolean isAllDone = todoList.getAreAllTasksDone() && (dukeList.getTasksCounter() > 0)
                && deadLineList.getAreAllTasksDone() && eventLists.getAreAllTasksDone();

        boolean isNothingDone = todoList.getAreAllTasksNotDone() && deadLineList.getAreAllTasksNotDone()
                && eventLists.getAreAllTasksNotDone() && (dukeList.getTasksCounter() > 0);

        if (isAllDone) {
            dukeList.printGoodEnding();
        } else if (isNothingDone) {
            dukeList.printBadEnding();
        } else {
            dukeList.printTraitor();
        }
    }

}
