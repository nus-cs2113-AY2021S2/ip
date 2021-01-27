package com.testsmarker;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println("Hello! I'm Duke"); //greets
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner in = new Scanner(System.in);

        //echoes user inputs:
        String input = "";
        tasks list[] = new tasks[100];
        int itemCount = 0;
        input = in.nextLine();
        while (!input.equals("bye")) {

            System.out.println(line);
            if (input.equals("list")){
                System.out.println("Here are the tasks in your lists:");
                for (int i = 0; i < itemCount; i++){
                    System.out.println(i+1 + ".[" + list[i].getStatusIcon() + "] "+ list[i].getDescription());
                }
            }
            else if (input.startsWith("done")){
                String[] word = input.split(" ");
                int index = Integer.parseInt(word[1]) - 1; //obtain task number(which starts from 1)
                list[index].markAsDone(); //mark task by current command as done
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + list[index].getStatusIcon() + "] " + list[index].getDescription());
            }
            else {
                System.out.println("added: " + input); //output based on user input
                list[itemCount] = new tasks(input); //create new task "input" and store to array 'list'
                itemCount++;
            }
            System.out.println(line);
            input = in.nextLine();
        }

        //exits with "bye":
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!"); //exits
        System.out.println(line);

    }
}