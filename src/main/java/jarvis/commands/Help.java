package jarvis.commands;

public class Help extends Command {

    private static void print(String sentence) {
        System.out.println(sentence);
    }

    public static void execute() {
        print("\tPerhaps this will help you in getting started sir:");
        print("\t\t1. todo <description>                   - Adds a new Todo task");
        print("\t\t2. deadline <description> /by <details> - Adds a new Deadline task");
        print("\t\t3. event <description> /at <details>    - Adds a new Event task");
        print("\t\t4. delete <task number>                 - Deletes the task");
        print("\t\t5. done <task number>                   - Marks the task as done");
        print("\t\t6. list                                 - Displays all the tasks");
        print("\t\t7. find <keyword>                       - Search for all the tasks with the keyword");
        print("\t\t8. bye                                  - Exits JARVIS");
    }
}
