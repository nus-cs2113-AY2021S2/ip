package dukchess.utility;

/**
 * Convenience class for all utilities required in a standard application
 */
public final class Utils {
    /**
     * Utility for checking if a string represents a numeric value.
     * Source: https://www.baeldung.com/java-check-string-number
     * @param strNum - a string that can potentially represent a numeric value
     * @return true if numeric, false otherwise
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
