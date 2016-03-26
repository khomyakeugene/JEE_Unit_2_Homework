package com.company.utils;

/**
 * Created by Yevgen on 06.01.2016.
 */

public class Utils {
    public static final String DONE_MESSAGE = "done";
    public final static String MESSAGE_WITH_PREFIX_PATTERN = "<%s>: %s";

    public static void printLine(String message) {
        System.out.print(message);
    }

    public static void rePrintLine(String message) {
        printLine("\r" + message);
    }

    public static void printMessage(String message) {
        printLine(message + "\n");
    }

    public static void printMessage(Object object, String message) {
        printMessage(String.format(MESSAGE_WITH_PREFIX_PATTERN, object.getClass().getName(), message));
    }

    public static void printDoneMessage() {
        printMessage(DONE_MESSAGE);
    }
}