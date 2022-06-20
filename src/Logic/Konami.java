package Logic;

public class Konami {
    private static int konamiIndex = 0;
    private static final char[] kCode = {'w', 'w', 's', 's', 'a', 'd', 'a', 'd', 'b', 'a'};

    /**
     * Checks if the konami code has been typed.
     * Does this by going one character at a time per call.
     */
    public static boolean check(String input) {
        if (input.length() == 0) return false;

        if(kCode[konamiIndex] == input.charAt(0)) {
            konamiIndex++;
            return konamiIndex == kCode.length;
        }
        return false;
    }
}