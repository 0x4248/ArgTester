/**
 * ArgTester
 * Send loads of arguments to a program to test it responses.
 * GitHub: https://www.guthub.com/0x4248/ArgTester
 * Licence: GNU General Public License v3.0
 * By: 0x4248
 */

package com.github.0x4248;
import java.io.*;
import java.util.*;
public class CommandRunner {
    public int execute(String command) {
        System.out.println(ConsoleColour.BLUE+"COMMAND =>\t"+ConsoleColour.RESET+command);
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(ConsoleColour.GREEN+"OUTPUT =>\t"+ConsoleColour.RESET+line);
            }
            System.out.println(ConsoleColour.CYAN+"EXIT NUM =>\t"+ConsoleColour.RESET+process.exitValue());
            return process.exitValue();
        } catch (Exception e) {
            System.out.println(ConsoleColour.RED+"ERROR =>\t"+ConsoleColour.RESET+e.getMessage());
            return 1;
        }
    }
}
