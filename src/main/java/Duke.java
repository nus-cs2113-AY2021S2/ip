public class Duke {
    
	public static String border = "____________________________________________________________";
	public static String logo =  " ____        _        \n"
			+ "|  _ \\ _   _| | _____ \n"
			+ "| | | | | | | |/ / _ \\\n"
			+ "| |_| | |_| |   <  __/\n"
			+ "|____/ \\__,_|_|\\_\\___|\n";

	public static void main(String[] args) {
		System.out.println("Hello from\n" + logo);
		greeting();
		exit();
	}

	public static void greeting(){ 
		System.out.println(border + "\n" + "Hello! I'm Duke\nWhat can I do for you?\n" + border);
	}

	public static void exit(){
		System.out.println(border + "\n" + "Bye. Hope to see you again soon!\n" + border);
	}
}
