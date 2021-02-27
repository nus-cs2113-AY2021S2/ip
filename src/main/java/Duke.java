import java.io.*;
import java.util.*;

public class Duke {
    /**
     * This method saves the ArrayList of tasks to duke.txt
     * @param taskItems
     */
    public static void saveToFile(ArrayList<Task> taskItems){
        String filename = "duke.txt";

        try {
            FileOutputStream fileStream = new FileOutputStream(filename);
            ObjectOutputStream outStream = new ObjectOutputStream(fileStream);

            outStream.writeObject(taskItems);

            fileStream.close();
            outStream.close();

            System.out.println("taskItems have been serialized");
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method of this project.
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<Task> taskItems = new ArrayList<Task>();
        int itemCount = 0;
        try {
            File dukeFile = new File("duke.txt");
            dukeFile.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader("duke.txt"));
            if (br.readLine()!= null) {
                System.out.println("Retrieving list details...");
                FileInputStream fileStream = new FileInputStream("duke.txt");
                ObjectInputStream inStream = new ObjectInputStream(fileStream);

                taskItems = (ArrayList<Task>)inStream.readObject();

                fileStream.close();
                inStream.close();
            }
        }catch(IOException e) {
            System.out.println("IOException occurred");
            e.printStackTrace();
        }catch(ClassNotFoundException e) {
            System.out.println("ClassNotFoundException occurred");
            e.printStackTrace();
        }

        String logo = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n\n"
                +"____________________________________________________________\n";
        System.out.println(logo);
        Scanner stringScanner = new Scanner(System.in);
        String userQuery = stringScanner.nextLine();
        String userQueryReturn = "";

        while(!userQuery.equalsIgnoreCase("bye")){
            if(userQuery.contains("find")) {
                String findKeyword = userQuery.substring(5);
                ArrayList<Task> matchingItems = new ArrayList<Task>();
                int matchingCount = 0;
                for(int i=0; i<itemCount; i++){
                    if(taskItems.get(i).getDescription().contains(findKeyword)){
                        matchingItems.add(taskItems.get(i));
                        matchingCount++;
                    }
                }
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the matching tasks in your list: ");
                for(int i=0; i<matchingCount; i++){
                    System.out.println(" "+String.valueOf(i+1)+".["+matchingItems.get(i).getTypeIcon()+"]["+matchingItems.get(i).getStatusIcon()+"] "+matchingItems.get(i).getDescription());
                }
                System.out.println("____________________________________________________________\n");
                userQuery = stringScanner.nextLine();
            }else if(userQuery.contains("done")) {
                int doneIndex = Integer.parseInt(userQuery.substring(5));
                taskItems.get(doneIndex - 1).setStatus(true);
                userQueryReturn = "____________________________________________________________\n"
                        + " Nice! I've marked this task as done: \n"
                        + " [" + taskItems.get(doneIndex - 1).getTypeIcon() + "][" + taskItems.get(doneIndex - 1).getStatusIcon() + "] " + taskItems.get(doneIndex - 1).getDescription() + "\n"
                        + "____________________________________________________________\n";
                System.out.println(userQueryReturn);
                saveToFile(taskItems);
                userQuery = stringScanner.nextLine();
            }else if(userQuery.contains("delete")){
                int doneIndex = Integer.parseInt(userQuery.substring(7));
                Task removedItem = taskItems.remove(doneIndex);
                itemCount--;
                userQueryReturn = "____________________________________________________________\n"
                        + " Noted. I've removed this task: \n"
                        + "   [" + removedItem.getTypeIcon() + "][" + removedItem.getStatusIcon() + "] " + removedItem.getDescription() + "\n"
                        + " Now you have "+ taskItems.size() + " tasks in the list.\n"
                        + "____________________________________________________________\n";
                System.out.println(userQueryReturn);
                saveToFile(taskItems);
                userQuery = stringScanner.nextLine();
            }else if(userQuery.contains("todo")||userQuery.contains("deadline")||userQuery.contains("event")) {
                taskItems.add(new Task(userQuery));
                itemCount++;
                if(userQuery.equalsIgnoreCase("todo") || userQuery.equalsIgnoreCase("deadline") || userQuery.equalsIgnoreCase("event")){
                    userQueryReturn = "____________________________________________________________\n"
                            + taskItems.get(taskItems.size() - 1).getDescription()
                            + "\n____________________________________________________________\n";
                }else {
                    userQueryReturn = "____________________________________________________________\n"
                            + " Got it. I've added this task: \n"
                            + "   [" + taskItems.get(taskItems.size() - 1).getTypeIcon() + "][" + taskItems.get(taskItems.size() - 1).getStatusIcon() + "] " + taskItems.get(taskItems.size() - 1).getDescription() + "\n"
                            + " Now you have " + taskItems.size() + " tasks in the list.\n"
                            + "____________________________________________________________\n";
                }
                System.out.println(userQueryReturn);
                saveToFile(taskItems);
                userQuery = stringScanner.nextLine();
            }else if(userQuery.equalsIgnoreCase("list")){
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list: ");
                for(int i=0; i<itemCount; i++){
                    System.out.println(" "+String.valueOf(i+1)+".["+taskItems.get(i).getTypeIcon()+"]["+taskItems.get(i).getStatusIcon()+"] "+taskItems.get(i).getDescription());
                }
                System.out.println("____________________________________________________________\n");
                userQuery = stringScanner.nextLine();
            }else{
                System.out.println("____________________________________________________________");
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("____________________________________________________________\n");
                userQuery = stringScanner.nextLine();
            }
        }
        String bye = "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n\n"
                +"____________________________________________________________\n";
        System.out.println(bye);
    }
}
