/**
 * ArgTester
 * Send loads of arguments to a program to test it responses.
 * GitHub: https://www.guthub.com/lewisevans2007/ArgTester
 * Licence: GNU General Public License v3.0
 * By: Lewis Evans
 */

package com.github.lewisevans2007;

public class Logger {
    public boolean silent = false;
    public void log(String message) {
        if (silent) return;
        System.out.println("[" + ConsoleColour.CYAN + " LOG " + ConsoleColour.RESET + "] " + message);
    }
    public void warn(String message) {
        System.out.println("[" + ConsoleColour.YELLOW + " WARN " + ConsoleColour.RESET + "] " + message);
    }
    public void error(String message) {
        System.out.println("[" + ConsoleColour.RED + "ERROR" + ConsoleColour.RESET + "] " + ConsoleColour.RED_BOLD_BRIGHT + message + ConsoleColour.RESET);
    }
    public void custom(String prefix, String message, Boolean run_if_silent) {
        if (silent && !run_if_silent) return;
        System.out.println("[" + prefix + "] " + message);
    }

}
