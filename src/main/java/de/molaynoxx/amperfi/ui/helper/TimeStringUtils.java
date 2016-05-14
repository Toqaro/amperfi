package de.molaynoxx.amperfi.ui.helper;

public class TimeStringUtils {

    public static String millisToString(long millis) {
        int length = (int) Math.floor(millis / 1000D);
        int hours = (int) Math.floor(length / 60D / 60D);
        length -= hours * 60 * 60;
        int minutes = (int) Math.floor(length / 60D);
        length -= minutes * 60;
        return (hours > 0 ? hours + ":" : "") + (minutes < 10 & hours > 0 ? "0" : "") + minutes + ":" + (length < 10 ? "0" + length : length);
    }

}
