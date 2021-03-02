package storage;

import myexceptions.FileModifiedException;

public class FileIntegrityChecker {

    public static boolean isCorrupted(String nextLine) throws FileModifiedException {
        Character index0 = nextLine.charAt(0);
        Character taskType = nextLine.charAt(1);
        Character index2 = nextLine.charAt(2);
        Character index3 = nextLine.charAt(3);
        Character taskStatus = nextLine.charAt(4);
        Character index5 = nextLine.charAt(5);
        Integer startOfDateIndex = nextLine.indexOf("<<");
        Integer endOfDateIndex = nextLine.lastIndexOf(">>");

        if(!index0.equals('[') || !index2.equals(']') || !index3.equals('[') || !index5.equals(']')) {
            return true;
        }else if(!taskStatus.equals('1') && !taskStatus.equals('0')){
            return true;
        }else if(!taskType.equals('T') && !taskType.equals('E') &&
                !taskType.equals('D')) {
            return true;
        }else if(taskType.equals('E') && (startOfDateIndex == -1 ||
                endOfDateIndex == -1)) {
            return true;
        }else if(taskType.equals('D') && (startOfDateIndex == -1 ||
                endOfDateIndex == -1)) {
            return true;
        }
        return false;
    }
}
