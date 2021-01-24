import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n\n"
                +"____________________________________________________________\n";
        System.out.println(logo);
        Scanner stringScanner = new Scanner(System.in);
        String userQuery = stringScanner.nextLine();
        String userQueryReturn = "";
        //String[] listItems = new String[100];
        ArrayList<Task> taskItems = new ArrayList<Task>();
        int itemCount = 0;
        while(!userQuery.equalsIgnoreCase("bye")){
            taskItems.add(new Task(userQuery));
            if(userQuery.contains("done")){
                int doneIndex = Integer.parseInt(userQuery.substring(5));
                taskItems.get(doneIndex-1).setStatus(true);
                userQueryReturn = "____________________________________________________________\n"
                        +" Nice! I've marked this task as done: \n"
                        + " ["+taskItems.get(doneIndex-1).getStatusIcon()+"] "+taskItems.get(doneIndex-1).getDescription()+"\n"
                        + "____________________________________________________________\n";
                System.out.println(userQueryReturn);
                userQuery = stringScanner.nextLine();
            }else if(!userQuery.equalsIgnoreCase("list")) {
                userQueryReturn = "____________________________________________________________\n"
                        + " added: " + userQuery + "\n"
                        + "____________________________________________________________\n";
                System.out.println(userQueryReturn);
                userQuery = stringScanner.nextLine();
                taskItems.add(new Task(userQuery));
                itemCount++;
            }else{
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list: ");
                for(int i=0; i<itemCount; i++){
                    System.out.println(" "+String.valueOf(i+1)+".["+taskItems.get(i).getStatusIcon()+"] "+taskItems.get(i).getDescription());
                }
                System.out.println("____________________________________________________________\n");
                userQuery = stringScanner.nextLine();
            }
            //itemCount++;
        }
        String bye = "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n\n"
                +"____________________________________________________________\n";
        System.out.println(bye);
    }
}
