import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class,Parser is used to analyse user commands.
 */

public class Parser {

    /**
     * This method just prints a line.
     */

    public static void displayLine(){
        System.out.println("_____________________________");
    }

    /**
     * Removes the requested task suggested by the user from the tasklist.
     *
     * @param request A string that contains the value of the index.
     * @param list lists the tasklist object.
     * @throws InvalidDeleteException will only be thrown if the delete function is not in the right format.
     */

    public static void delete(String request, TaskList list) throws InvalidDeleteException{
        try {

            int index = Integer.parseInt(request);
            System.out.println("Noted.I've removed this task\n" + list.get(index));
            list.delete(index);
            System.out.println("Now you have " + list.getSize() + " tasks in the list");
            displayLine();

        } catch (NumberFormatException e) {

            System.out.println("You have not provided a valid number");
            displayLine();

        }
    }

    /**
     * Adds a todo task to the tasklist array.
     * @param request A string that contains the value of the index.
     * @param list lists the tasklist object.
     */

    public static void addTodo(String request, TaskList list){
        try {

            ToDos todo = getTodo(request);
            System.out.println("Got it. I've added this task:\n" + todo);
            list.update(todo);
            System.out.println("Now you have " + list.getSize() + " tasks in the list.");
        } catch (ExceptionToDo ex) {
            System.out.println("Oops!!! I'm sorry, but the description of a todo cannot be empty");
        }
    }

    /**
     * Adds a deadline task to the tasklist array
     *
     * @param word the description provided by the user.
     * @param list lists the tasklist object
     * @throws InvalidDeadlineException
     */

    public static void getDeadline(String word, TaskList list) throws InvalidDeadlineException {
        if (word.contains("/by") && !word.substring(word.indexOf("/by") + 3).equals("")){
            word = word.substring(8);
            int index = word.indexOf("/by");
            String str = word.substring(index + 3).trim();
            String dateString = str.replaceAll("-", "/");
            String[] dateArray = dateString.split("/");
            if (dateArray.length < 2){
                throw new InvalidDeadlineException();
            }
            if (dateArray[0].length() < 2){
                dateString = "0" + dateString;
                dateArray[0] = "0" + dateArray[0];
            }
            if (dateArray[1].length() < 2){
                dateString = dateArray[0] + "/0" + dateArray[1] + "/" + dateArray[2];
            }
            if (!dateString.contains(":")){
                String[] arr = dateString.split(" ");
                if (arr.length != 2){
                    throw new InvalidDeadlineException();
                }
                arr[1] = arr[1].substring(0, 2) + ":" + arr[1].substring(2);
                dateString = arr[0] + " " + arr[1];
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime date = LocalDateTime.parse(dateString, formatter);
                Deadline deadline = new Deadline(word.substring(0, index), date);
                list.update(deadline);
                System.out.println("Got it. I've added this task:\n " + deadline);
                System.out.println("Now you have " + list.getSize() + " tasks in the list.");
            } catch (DateTimeParseException e){
                System.out.println("Invalid date provided");
            }
            displayLine();
        } else {
            throw new InvalidDeadlineException();
        }
    }

    /**
     * Adds an event task to the tasklist array
     *
     * @param word the description provided by the user.
     * @param list lists the tasklist object.
     * @throws InvalidEventException
     */

    public static void getEvent(String word, TaskList list) throws InvalidEventException {
        if (word.contains("/at") && !word.substring(word.indexOf("/at") + 3).equals("")){
            word = word.substring(5);
            int index = word.indexOf("/at");
            String str = word.substring(index + 3).trim();
            String dateString = str.replaceAll("-", "/");
            String[] dateArray = dateString.split("/");
            if (dateArray.length < 2){
                throw new InvalidEventException();
            }
            if (dateArray[0].length() < 2){
                dateString = "0" + dateString;
                dateArray[0] = "0" + dateArray[0];
            }
            if (dateArray[1].length() < 2){
                dateString = dateArray[0] + "/0" + dateArray[1] + "/" + dateArray[2];
            }
            if (!dateString.contains(":")){
                String[] arr = dateString.split(" ");
                if (arr.length != 2){
                    throw new InvalidEventException();
                }
                arr[1] = arr[1].substring(0, 2) + ":" + arr[1].substring(2);
                dateString = arr[0] + " " + arr[1];
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime date = LocalDateTime.parse(dateString, formatter);
                Event event =  new Event(word.substring(0, index), date);
                System.out.println("Got it. I've added this task:\n" + event);
                list.update(event);
                System.out.println("Now you have " + list.getSize() + " tasks in the list.");
            } catch (DateTimeParseException e){
                System.out.println("Incorrect Date format used");
            }
            displayLine();
        } else {
            throw new InvalidEventException();
        }
    }

    /**
     * This method is used the find any keyword inputted by the user.
     * @param input This is the keyword that will be found in the tasklist.
     * @param tasks All the tasks from which the keyword will be found from.
     * @throws InvalidNumberException
     */

    public static void find(String input, TaskList tasks) throws InvalidNumberException{
        String[] requests = input.split(" ");
        if (requests.length != 2){
            throw new InvalidNumberException("You have inputted more than one word for find");
        }
        for (int i = 1; i <= tasks.getSize(); i++){
            if (tasks.get(i).getWork().contains(requests[1])){
                System.out.println(tasks.get(i));
            }
        }
        displayLine();
    }

    /**
     * This method will update a particular task to done.
     * @param request A string that contains the value of the index.
     * @param list lists the tasklist object.
     */

    public static void update(String request, TaskList list) {
        assert !request.equals("") : "Input is invalid";
        try {
            int index = Integer.parseInt(request);
            list.updateStatus(index);
            String str = "Nice! I've marked this task as done: \n "
                    + list.get(index);
            str += ". Now you have " + list.getSize() + " tasks in the list";
        } catch (NumberFormatException ignored) {
        }
    }

    /**
     * Adds a todo object containing the descriptions provided the list of tasks
     * @param work A string object that has the right description.
     * @return The String that contains the information about the todo object.
     * @throws ExceptionToDo
     */

    public static ToDos getTodo(String work) throws ExceptionToDo{
        if (work.length() > 4){
            return new ToDos(work.substring(4));
        } else {
            throw new ExceptionToDo();
        }
    }
}