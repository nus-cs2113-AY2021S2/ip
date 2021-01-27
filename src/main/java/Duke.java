import java.util.Scanner;

public class Duke {
    public static String[] addX(int n, String arr[], String x)
    {
        // create a new array of size n+1
        String newarr[] = new String[n + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (int i = 0; i < n; i++)
            newarr[i] = arr[i];
        newarr[n] = x;
        return newarr;
    }

    public static void main(String[] args) {
        String[] list = new String[0];
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        Scanner in = new Scanner(System.in);
            while (true) {
                String line;
                line = in.nextLine();
                if (line.equals("bye")){
                    break;
                }
                else if(line.equals("list")){
                    for (int i=0; i<list.length; i++){
                        System.out.println(i + ". " + list[i] );
                    }
                }
                else{
                    list = addX(list.length, list, line);
                    System.out.println("added: " + line);
                }
            }

        System.out.println("____________________________________________________________\n");
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println("____________________________________________________________\n");
    }
}
