import java.util.Scanner;

public class Duke {
    public static final int maxTasks = 100;
    private static final String border = "    ―――――――――――――――――――――――――――――――――――――――――――――――――";
    private static Task[] tasks = new Task[maxTasks];

    public static void main(String[] args) {
        welcomeMessage();
        Scanner in = new Scanner(System.in);
        boolean isOn = true;
        int numOfCompletedTasks = 0;
        int numOfTasks = 0;
        int n = 0;
        while(isOn && in.hasNextLine()) {
            String line = in.nextLine();
            String[] words = line.split(" ");
            try{
                if(line.equals("bye")){
                    System.out.println(border);
                    System.out.println("    Bye. Hope to see you again soon!");
                    System.out.println(" ");
                    System.out.println(border);
                    isOn = false;
                } else if(words[0].equals("todo")){
                    String description = "";
                    try{
                        int wordsCount = words.length;
                        if(words.length==1){
                            throw new DukeException("missing_parameter");
                        }
                        for(String word:words){
                            wordsCount--;
                            if(word.equals("todo")){
                                continue;
                            } else if(wordsCount==0){
                                description = description + word;
                            } else{
                                description = description + word + " ";
                            }
                        }
                        tasks[n] = new Todo(description);
                        System.out.println(border);
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("      "+tasks[n]);
                        n++;
                        System.out.println("    Now you have " + n +" tasks in the list.");
                        System.out.println(" ");
                        System.out.println(border);
                    }catch (DukeException e) {
                        System.out.println(border);
                        System.out.println("    OOPS!! The description of a todo cannot be empty.");
                        System.out.println(border);
                    }
                } else if(words[0].equals("deadline")){
                    String description = "";
                    String by = "";
                    try{
                        boolean byFlag = false;
                        int wordsCount = words.length;
                        if(wordsCount==1){
                            throw new DukeException("missing_parameter");
                        }
                        for(String word:words){
                            wordsCount--;
                            if(word.equals("/by")){
                                byFlag = true;
                                continue;
                            }
                            if(word.equals("deadline")){
                                continue;
                            }
                            if(byFlag && wordsCount==0){
                                by = by + word;
                            } else if(byFlag){
                                by = by + word + " ";
                            } else{
                                description = description + word + " ";
                            }
                        }
                        tasks[n] = new Deadline(description,by);
                        System.out.println(border);
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("      "+tasks[n]);
                        n++;
                        System.out.println("    Now you have " + n +" tasks in the list.");
                        System.out.println(" ");
                        System.out.println(border);
                    }catch (DukeException e){
                        System.out.println(border);
                        System.out.println("    OOPS!! The description of the deadline cannot be empty.");
                        System.out.println(border);
                    }

                } else if(words[0].equals("event")){
                    String description = "";
                    String at = "";
                    try{
                        boolean atFlag = false;
                        int wordsCount = words.length;
                        if(wordsCount==1){
                            throw new DukeException("missing_parameter");
                        }
                        for(String word:words){
                            wordsCount--;
                            if(word.equals("/at")){
                                atFlag = true;
                                continue;
                            }
                            if(word.equals("event")){
                                continue;
                            }
                            if(atFlag && wordsCount==0){
                                at = at + word;
                            } else if(atFlag){
                                at = at + word + " ";
                            } else{
                                description = description + word + " ";
                            }
                        }
                        tasks[n] = new Event(description,at);
                        System.out.println(border);
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("      "+tasks[n]);
                        n++;
                        System.out.println("    Now you have " + n +" tasks in the list.");
                        System.out.println(" ");
                        System.out.println(border);
                    }catch (DukeException e){
                        System.out.println(border);
                        System.out.println("    OOPS!! The description of the deadline cannot be empty.");
                        System.out.println(border);
                    }

                } else if(words[0].equals("done")){
                    int taskNum = Integer.parseInt(words[1])-1;
                    tasks[taskNum].markAsDone();
                    System.out.println(border);
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("      " + tasks[taskNum]);
                    System.out.println(" ");
                    System.out.println(border);
                } else if(line.equals("list")){
                    System.out.println(border);
                    System.out.println("    Here are the tasks in your list:");
                    for(int i=0;i<n;i++){
                        System.out.println("    "+(i + 1) + "." + tasks[i]);
                    }
                    System.out.println(" ");
                    System.out.println(border);
                } else {
                    throw new DukeException("unrecognized_input");
                }
            }catch(DukeException e){
                System.out.println(border);
                System.out.println("    OOPS!! I'm sorry, but I don't know what that means.");
                System.out.println(border);
            }
        }
    }

    private static void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(" ");
        System.out.println("--------------------------------------------------------------------------------");
    }
}
