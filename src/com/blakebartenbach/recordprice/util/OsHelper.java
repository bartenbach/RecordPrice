package com.blakebartenbach.recordprice.util;

public class OsHelper {

    public static boolean isWindows() {
        String OS = System.getProperty("os.name").toLowerCase();
        return (OS.contains("win"));
    }

    public static boolean isMac() {
        String OS = System.getProperty("os.name").toLowerCase();
        return (OS.contains("mac"));
    }

    public static boolean isUnix() {
        String OS = System.getProperty("os.name").toLowerCase();
        return (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0 );
    }

    public static boolean isSolaris() {
        String OS = System.getProperty("os.name").toLowerCase();
        return (OS.contains("sunos"));
    }
}
