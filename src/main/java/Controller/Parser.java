package Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class Parser {

    public String[] processInput(String in) {
        String[] strings = new String[2];
        int index = in.indexOf(" ");
        String subString1 = in.substring(index+1);
        if (!subString1.contains("/")) {
            strings[0] = subString1;
            return strings;
        }
        else {
            int index1 = subString1.indexOf("/");
            String subString2 = subString1.substring(0, index1-1);
            String subString3 = subString1.substring(index1);
            int index2 = subString3.indexOf(" ");
            String subString4 = subString3.substring(index2+1);
            strings[0] = subString2;
            strings[1] = subString4;
            return strings;
        }
    }

    public LocalDate processString(String[] strings) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate datetime = LocalDate.parse(strings[0], formatter);
        return datetime;
    }


    public int charNumber(String in) {
        char ch1, ch2;
        for (int i = 0; i <in.length(); i++){
            ch1 = in.charAt(i);
            int index = i;
            if (++index == in.length()){
                if(Character.isDigit(ch1)) {
                    return Character.getNumericValue(ch1);
                }
            }
            ch2 = in.charAt(index);
            if(Character.isDigit(ch1)&&Character.isDigit(ch2)) {
                return Character.getNumericValue(ch1)*10 + Character.getNumericValue(ch2);
            }
        }
        return 0;
    }
}
