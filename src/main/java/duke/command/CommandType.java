package duke.command;

/**
 * All command types used in the program.
 * LIST: lists tasks
 * DATE: lists deadlines/events on given date
 * DONE: mark task as done
 * TODO: create new todo
 * DEADLINE: create new deadline
 * EVENT: create new event
 * DELETE: delete tasks
 * FIND: find tasks based on keyword(s) of task description
 * BYE: exit program
 * HELP: prints usage information for Duke
 */
public enum CommandType {
    LIST, DONE, BYE, TODO, DEADLINE, EVENT, DELETE, DATE, FIND, HELP;
}
