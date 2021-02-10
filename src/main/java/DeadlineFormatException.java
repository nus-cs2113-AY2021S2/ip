public class DeadlineFormatException extends TaskFormatException{
    @Override
    public String toString(){
        return new String("    Name of deadline should be specified after keyword deadline. " +
                "Name and the time to finish should be separated by \"/by\"\n" +
                super.toString());
    }
}
