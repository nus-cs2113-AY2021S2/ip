public class TodoFormatException extends TaskFormatException{
    @Override
    public String toString(){
        return new String("    Name of Todo should be specified after todo keyword\n" +
                super.toString());
    }
}
