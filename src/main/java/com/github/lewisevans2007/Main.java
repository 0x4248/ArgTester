/**
 * ArgTester
 * Send loads of arguments to a program to test it responses.
 * GitHub: https://www.guthub.com/lewisevans2007/ArgTester
 * Licence: GNU General Public License v3.0
 * By: Lewis Evans
 */


package com.github.lewisevans2007;
import java.util.*;
import java.io.*;
public class Main {
    public static String version = "1.0.0";

    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.silent = false;
        if (args.length < 2) {
            System.out.println("Usage: java -jar ArgTester.jar [COMMAND] [ARGUMENTS] Other arguments");
            System.exit(1);
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-v") || args[i].equals("--version")) {
                System.out.println("ArgTester v"+version);
                System.exit(0);
            } else if (args[i].equals("-h") || args[i].equals("--help")) {
                System.out.println("Usage: java -jar ArgTester.jar [COMMAND] [ARGUMENTS] Other arguments");
                System.out.println("Other arguments:");
                System.out.println("-v, --version: Print the version of ArgTester");
                System.out.println("-h, --help: Print the help message");
                System.out.println("-s, --silent: Don't print all logs");
                System.out.println("-o [FILE]: Where to write the report in Markdown format");
                System.exit(0);
            } else if (args[i].equals("-s") || args[i].equals("--silent")) {
                logger.silent = true;
            }
        }
        logger.log("ArgTester v"+version);
        String command = args[0];
        String[] files = args[1].split(",");

        int fileLength = 0;
        for (String file : files) {
            logger.log("Reading file: " + file);
            int length = 0;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                while (reader.readLine() != null) {
                    length++;
                }
                reader.close();
            } catch (IOException e) {
                logger.error("IO Error: " + e.getMessage());
                System.exit(1);
            }
            if (fileLength == 0) {
                fileLength = length;
            } else if (fileLength != length) {
                logger.error("The files don't have the same amount of arguments");
                System.exit(1);
            }
        }
        logger.log("Arguments to run: " + fileLength);
        for (int i = 0; i < fileLength; i++) {
            String output = command;
            for (int j = 0; j < files.length; j++) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(files[j]));
                    String line = reader.readLine();
                    for (int k = 0; k < i; k++) {
                        line = reader.readLine();
                    }
                    output = output.replace("{" + (j + 1) + "}", line);
                    reader.close();
                } catch (IOException e) {
                    logger.error("IO Error: " + e.getMessage());
                    System.exit(1);
                }
            }
            logger.log("Running command: " + output);
            CommandRunner runner = new CommandRunner();
            int exitCode = runner.execute(output);
            if (exitCode != 0) {
                logger.error("Command exited with code " + exitCode);
                System.exit(1);
            }

        }
    }
}