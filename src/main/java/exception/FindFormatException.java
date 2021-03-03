package exception;

public class FindFormatException extends RuntimeException{
    public FindFormatException(){}

    @Override
    public String toString(){
        return new String("Invalid input format for find command.\n" +
                "    Input format for find: find + the keyword    eg. find book");
    }
}
