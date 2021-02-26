package tasklist;

import storage.Storage;

public class TaskList extends Storage {

    public static void list() {
        for (int i = 0; i < taskArrayList.size(); i++) {
            System.out.print(i + 1 + ".");
            taskArrayList.get(i).printStatus();
        }
    }

    public static void done(String userInput) {
        String text;
        int index = Integer.parseInt(userInput.substring(5, userInput.length()));
        tasks[index - 1].setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        tasks[index - 1].printStatus();
        text = taskSentences.get(index - 1);
        text = text.replace("false", "true");
        taskSentences.set(index - 1, text); //rewrite and update the status
        Storage.writeFile(); //update file
    }

    public static void delete(String userInput) {
        int index = Integer.parseInt(userInput.substring(7, userInput.length()));
        System.out.println("Noted. I've removed this task:");
        taskArrayList.get(index - 1).printStatus(); //print the task to be remove before delete
        taskArrayList.remove(index - 1); //remove obj
        taskSentences.remove(index - 1); //remove sentence from file
        count = count - 1; //update count value after deleting
        System.out.println("Now you have " + taskArrayList.size() + " tasks in the list.");
        Storage.writeFile(); //update file
    }

    public static void find(String userInput) {

            String keyword = userInput.substring(5, userInput.length());
            int index = 1;
            int rememberIndex=-1;
            boolean hasFound = false;



            for (int i = 0; i < taskArrayList.size(); i++) {        //first loop to check if there is at least 1 match
                if (taskArrayList.get(i).getName().contains(keyword)) {
                    rememberIndex = i;
                    hasFound = true;
                    break;                                          //break out of loop after finding 1 match
                }
            }

            if(hasFound){
                System.out.println("Here are the matching tasks in your list:");
                for (int i = rememberIndex; i < taskArrayList.size(); i++) {    //loop again starting from the 1st match
                    if (taskArrayList.get(i).getName().contains(keyword)) {
                        System.out.print(index++ + ".");
                        taskArrayList.get(i).printStatus();
                    }
                }
            } else{
                System.out.println("No matching task found.");
            }

        }
    }

