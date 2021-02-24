package duke;

import java.util.ArrayList;

public class Keyword {

    public static ArrayList<String> keywords = new ArrayList<>();

    public static String getKeywords(int index) {
        return keywords.get(index);
    }

    public static void setKeywords(String alphabet) {
        keywords.add(alphabet);
    }

    public static void removeKeyword(int index) {
        keywords.remove(index);
    }

}
