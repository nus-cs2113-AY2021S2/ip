public class Duke {
    
	public static final String border = "____________________________________________________________";
	public static final String logo =  " ____        _        \n"
			+ "|  _ \\ _   _| | _____ \n"
			+ "| | | | | | | |/ / _ \\\n"
			+ "| |_| | |_| |   <  __/\n"
			+ "|____/ \\__,_|_|\\_\\___|\n";

	public static void greeting(){ 
		System.out.println(border + "\n" + "Hello! I'm Duke\nWhat can I do for you?");
	}

	public static void exit(){
		System.out.println(border + "\n" + "Bye. Hope to see you again soon!\n" + border);
	}
	
	public static void main(String[] args) {
		System.out.println("Hello from\n" + logo);
		greeting();
		exit();
	}
	
}
