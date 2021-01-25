import java.util.Scanner;

public class Duke {
    public static void greet() {
        String logo = "             ⣠⣤⣤⣤⣤⣤⣶⣦⣤⣄⡀" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⠀ ⢀⣴⣿⡿⠛⠉⠙⠛⠛⠛⠛⠻⢿⣿⣷⣤⡀" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⠀ ⣼⣿⠋⠀⠀⠀⠀⠀⠀⠀  ⢀⣀⣀⠈⢻⣿⣿⡄" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀ ⣸⣿⡏⠀⠀⠀ ⣠⣶⣾⣿⣿⣿⠿⠿⠿⢿⣿⣿⣿⣄" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀ ⣿⣿⠁⠀⠀ ⢰⣿⣿⣯⠁⠀⠀⠀⠀⠀⠀⠀ ⠈⠙⢿⣷⡄" + System.lineSeparator()
                + " ⠀⠀⣀⣤⣴⣶⣶⣿⡟⠀⠀⠀ ⢸⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⣿⣷" + System.lineSeparator()
                + " ⠀⢰⣿⡟⠋⠉⣹⣿⡇⠀⠀⠀ ⠘⣿⣿⣿⣿⣷⣦⣤⣤⣤⣶⣶⣶⣶⣿⣿⣿" + System.lineSeparator()
                + " ⠀⢸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀ ⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃" + System.lineSeparator()
                + " ⠀⣸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀ ⠉⠻⠿⣿⣿⣿⣿⡿⠿⠿⠛⢻⣿⡇" + System.lineSeparator()
                + " ⠀⣿⣿⠁⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⢸⣿⣧" + System.lineSeparator()
                + " ⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⢸⣿⣿" + System.lineSeparator()
                + " ⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⢸⣿⣿" + System.lineSeparator()
                + " ⠀⢿⣿⡆⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⢸⣿⡇" + System.lineSeparator()
                + " ⠀⠸⣿⣧⡀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⣿⣿⠃" + System.lineSeparator()
                + " ⠀⠀⠛⢿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⣰⣿⣿⣷⣶⣶⣶⣶⠶⠀⢠⣿⣿" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⣽⣿⡏⠁⠀⠀  ⢸⣿⡇" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⢹⣿⡆⠀⠀⠀  ⣸⣿⠇" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⢿⣿⣦⣄⣀⣠⣴⣿⣿⠁⠀⠈⠻⣿⣿⣿⣿⡿⠏" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⠈⠛⠻⠿⠿⠿⠿⠋⠁" + System.lineSeparator();

        System.out.println("Ssshhhhhh!!!!!" + System.lineSeparator() + logo);
        String greetings = "____________________________________________________________" + System.lineSeparator()
                + "Hello Crewmate! I'm Arthur, ( ͡° ͜ʖ ͡°)" + System.lineSeparator()
                + "Please assign me my tasks to complete!" + System.lineSeparator()
                + "____________________________________________________________" + System.lineSeparator();
        System.out.println(greetings);
    }
    public static void exit(){
        String bye="____________________________________________________________"+ System.lineSeparator()
                +"You have been kicked out! Bye!!!  (๑>ᴗ<๑)"+ System.lineSeparator()
                + "____________________________________________________________"+ System.lineSeparator();
        System.out.println(bye);

    }
    public static void echo(String line){
        line = "____________________________________________________________"+ System.lineSeparator()
                +line+System.lineSeparator()+"____________________________________________________________"+ System.lineSeparator();
        System.out.println(line);

    }

    public static void main(String[] args) {
        greet();
        boolean hasToExit = false;
        String line;
        Scanner in = new Scanner(System.in);
        while(!hasToExit){
            line = in.nextLine();
            switch (line){
            case "bye":
                exit();
                hasToExit = true;
                break;
            default:
                echo(line);
                break;
            }
        }
    }




}
