public class Ui {

    static final String SECTION_DIVIDER = "____________________________________________________________";

    static void greetUser(){
        String greeting = "\t" + SECTION_DIVIDER + "\n"
                + "\tHello! I'm Duke. \n"
                + "\tWhat can I do for you? \n"
                + "\t" + SECTION_DIVIDER;
        System.out.println(greeting);
    }

    static void signOff(){
        String sign_off = "\t" + SECTION_DIVIDER + "\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t" + SECTION_DIVIDER;
        System.out.println(sign_off);
    }
}
