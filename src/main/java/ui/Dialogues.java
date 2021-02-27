package ui;

/**
 * Represents all interactions
 */
public class Dialogues {

    public static final String THIS_JOB_WAS_ALREADY_COMPLETED = "This job was already completed!!!!";
    public static final String YOU_COMPLETED_ALL_YOUR_TASKS_IN_THIS_LIST =
            "Good job Crewmate! You completed all your tasks in this list! ( ^ _ ^ )";
    public static final String WHAT_ARE_YOU_DOING_THIS_JOB_WAS_ALREADY_COMPLETED =
            "What are you doing??? This job was already completed!! ( O . o )";
    public static final String TASK_LEFT_FIRST_PART = "You still have ";
    public static final String TASKS_LEFT_SECOND_PART = " tasks left in this list Crewmate! Hurry up!! ( X _ X )\"";
    public static final String FIRST_LINE = "Ssshhhhhh!!!!!";
    public static final String GREETING = "Hello Crewmate! I'm Arthur, ( = ^ _ ^ = )";
    public static final String ASSIGN_ME_MY_TASKS_TO_COMPLETE = "Please assign me my tasks to complete!";
    public static final String LOGO = "                             * ** ** ** ** ** ** ** *" + System.lineSeparator()
            + "                         ** ** ** ** ** ** ** ** ** ** *" + System.lineSeparator()
            + "                        ** ** ** ** ** ** ** ** ** ** ** ** *" + System.lineSeparator()
            + "                      * ** ** ** * ** ** ** ** ** ** ** ** **" + System.lineSeparator()
            + "                    ** ** **                     * ** ** ** **" + System.lineSeparator()
            + "                   ** ** *                         * ** ** ** *" + System.lineSeparator()
            + "                  ** **                                * ** ** **" + System.lineSeparator()
            + "                 * ** *                                   ** ** **" + System.lineSeparator()
            + "                ** **                                      * ** ** *" + System.lineSeparator()
            + "               ** **                           * ** ** ** * * ** ** *" + System.lineSeparator()
            + "              * ** **                    * ** ** ** ** ** ** ** ** ** **" + System.lineSeparator()
            + "              * ** *                 ** ** ** ** ** ** ** ** ** ** ** **" + System.lineSeparator()
            + "              * ** *               * ** ** ** ** ** ** ** ** ** ** ** ** *" + System.lineSeparator()
            + "              * ** *               ** ** ** ** **  *  *  *  *  ** * ** ** **" + System.lineSeparator()
            + "              * ** *              ** ** ** *                       * ** ** *" + System.lineSeparator()
            + "              * **              * ** ** *                           * ** **" + System.lineSeparator()
            + "              * **              * ** ** **                          ** ** *" + System.lineSeparator()
            + "            ** **              * ** ** **                         * ** *" + System.lineSeparator()
            + "            *  **              ** ** ** **                      ** ** *" + System.lineSeparator()
            + "           **  **               * ** ** ** *                * * ** **" + System.lineSeparator()
            + "        ** **  **                * ** ** ** ** ** ** ** ** ** ** ** *" + System.lineSeparator()
            + "     ** ** **  **                  * ** ** ** ** ** ** ** ** *" + System.lineSeparator()
            + "  ** ** ** **  **                                        ** **" + System.lineSeparator()
            + "  ** **    **  **                                        ** **" + System.lineSeparator()
            + "  ** *     **  **                                      * ** **" + System.lineSeparator()
            + "  ** *     **  **                                     * ** **" + System.lineSeparator()
            + "  ** *     **  **                                     ** ** *" + System.lineSeparator()
            + "  ** *     **  **                                     ** ** *" + System.lineSeparator()
            + "  ** *     **  **                                     ** ** *" + System.lineSeparator()
            + "  ** *     **  **                                     ** ** *" + System.lineSeparator()
            + "  ** *     **  **                                   * ** ** *" + System.lineSeparator()
            + "  ** *     **  **                                   * ** ** *" + System.lineSeparator()
            + "  ** *     **  **                                   * ** ** *" + System.lineSeparator()
            + "  ** *     **  **                                   * ** ** *" + System.lineSeparator()
            + "  ** *    **  **                                    * ** ** *" + System.lineSeparator()
            + "  ** ** ** **  **                                   * ** ** *" + System.lineSeparator()
            + "  ** ** ** **  **                                   * ** ** *" + System.lineSeparator()
            + "      * ** **  **                                   * ** ** *" + System.lineSeparator()
            + "        ** **  **                                   * ** ** *" + System.lineSeparator()
            + "         ** **  *              ** ** ** ** ** *        * **" + System.lineSeparator()
            + "            **  **             ** ** ** ** ** *        * **" + System.lineSeparator()
            + "             **  **             ** **     * ** *       * **" + System.lineSeparator()
            + "              *  **             ** **     ** **        * **" + System.lineSeparator()
            + "               **             ** **       ** **        * **" + System.lineSeparator()
            + "                **            ** **        * **        * **" + System.lineSeparator()
            + "                *            ** **          **        * **" + System.lineSeparator()
            + "                 **         ** *            **        **" + System.lineSeparator()
            + "                  * ** ** ** *               ** ** ** **" + System.lineSeparator()
            + "                   ** ** ***                  * ** **" + System.lineSeparator();
    public static final String HELP = "               help                 :prints list of all commands"
            + System.lineSeparator()
            + "               list                 :prints all lists ordered by category" + System.lineSeparator()
            + "               exit                 :shuts down Arthur" + System.lineSeparator()
            + "            todo <task>             :add task to To Do list" + System.lineSeparator()
            + "  event <task> // yyyy/MM/dd HHmm   :add task to Event List with time" + System.lineSeparator()
            + " deadline <task> // yyyy/MM/dd HHmm :add task to Deadline List" + System.lineSeparator()
            + "            done  <index>           :mark task <index> as done in List" + System.lineSeparator()
            + "            delete  <index>         :delete task <index> in List" + System.lineSeparator()
            + "            find  <task>            :find task(s) based on their description"
            + System.lineSeparator()
            + "         search yyyy/MM/dd          :search for task(s) based on their date"
            + System.lineSeparator();
    public static final String WRONG_DATE_FORMAT = "Wrong Date format Crewmate!!" + System.lineSeparator()
            + "Please re-enter the date ONLY in this format yyyy/MM/dd HHmm" + System.lineSeparator()
            + "PS: NO NEED to use event <task> nor deadline command <task> again!!";
    public static final String WRONG_DATE_FORMAT_SEARCH = "Wrong Date format Crewmate!!" + System.lineSeparator()
            + "Please re-enter the date ONLY in this format yyyy/MM/dd HHmm" + System.lineSeparator()
            + "PS: No need to use search command again";
    public static final String SEARCH_HAS_BEEN_COMPLETED = "Search has been completed!!";
    public static final String NO_MATCH_FOUND = "No match found!! （/ . \\）";
    public static final String LIST_OF_TASKS_CREWMATE = "ATTENTION, Here's your list of tasks Crewmate!!!";
    public static final String DOTTED_LINE =
            "____________________________________________________________________________________";
    public static final String NO_SUCH_COMMAND = "There's no such command!!! You look SUS!!!  ( O . o )";
    public static final String THIS_TASK_HAS_ALREADY_BEEN_COMPLETED =
            "This task has already been completed crewmate!! Watchu doin??? ( O . o )";
    public static final String NO_SUCH_TASK = "There's no such task?! Focus Crewmate!!  ( X _ X )\"";
    public static final String YOU_HAVEN_T_DONE_ANY_WORK_IN_THIS_LIST =
            "Are you really a Crewmate??? You haven't done any work in this list! ( X _ X ' )";
    public static final String COMPLETED_ALL_YOUR_TASKS_IN_THIS_LIST =
            "Good job Crewmate! You completed all your tasks in this list! ( ^ _ ^ )";
    public static final String THIS_LIST_IS_EMPTY = "This list is empty!!! YEEEEEEET!!!";
    public static final String YOU_STILL_HAVE = "You still have ";
    public static final String TASK_S_LEFT_IN_YOUR_LISTS = " task(s) left in your Lists ! Hurry up!! ( X _ X )";
    public static final String GOOD_ENDING = "Thanks for your help Crewmate!!" + System.lineSeparator()
            + "We wouldn't have done this without your help!!" + System.lineSeparator()
            + "Goodbye!!!! (^ . ^)";
    public static final String BAD_ENDING = "You have been kicked out! Bye Impostor!!!  ( X _ X \" )";
    public static final String TRAITOR = "You are abandoning us!!! I trusted you!!!  ( O . o ' )";
    public static final String YOUR_SEARCH_HAS_BEEN_COMPLETED_CREWMATE =
            "Your search has been completed Crewmate! ( ^ _ ^ )";
    public static final String LOADING_FILE = "Loading your previous data . . .";
    public static final String FILE_IS_EMPTY = "Your saved file is empty!";
    public static final String LOADING_WINDOW = "Loading New Duke Window . . .";
    public static final String DELETING_THIS_TASK = " ( o . ^ ) Hol' up! I'm deleting this task:";
}