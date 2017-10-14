package com.example.utility;

import java.util.Arrays;

/**
 * Created by kevinnkurniawan on 10/14/17.
 */
public class FileValidator {

    private static final String[] hexSymbols = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    public static final int BITS_PER_HEX_DIGIT = 4;
    public static final String XLSX = "504b03041400";
    public static final String XLS = "d0cf";
    public static final String JPEG = "ffd8";
    public static final String PNG = "8950";
    public static final String ZIP = "504b0304";
    public static final String PDF = "2550";
    public static final String GIF = "4749";

    public FileValidator() {
    }

    public static boolean checkFileByType(byte[] bytes, String type) {
        boolean isMatchType = false;

        try {
            byte[] bytesCut = Arrays.copyOfRange(bytes, 0, type.length() / 2);
            String hexHeader = toHexFromBytes(bytesCut);
            if (hexHeader.indexOf(type) != -1) {
                isMatchType = true;
            }

            Object var6 = null;
            return isMatchType;
        } catch (Exception var5) {
            throw new IllegalArgumentException(var5.getMessage(), var5);
        }
    }

    public static String toHexFromByte(byte b) {
        byte leftSymbol = (byte)(b >>> 4 & 15);
        byte rightSymbol = (byte)(b & 15);
        return hexSymbols[leftSymbol] + hexSymbols[rightSymbol];
    }

    public static String toHexFromBytes(byte[] bytes) {
        if (bytes != null && bytes.length != 0) {
            StringBuilder hexBuffer = new StringBuilder(bytes.length * 2);

            for(int i = 0; i < bytes.length; ++i) {
                hexBuffer.append(toHexFromByte(bytes[i]));
            }

            return hexBuffer.toString();
        } else {
            return "";
        }
    }
}
