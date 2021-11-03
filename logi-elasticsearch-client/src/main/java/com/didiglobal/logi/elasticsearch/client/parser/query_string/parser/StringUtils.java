package com.didiglobal.logi.elasticsearch.client.parser.query_string.parser;

public class StringUtils {
    public static String discardEscapeChar(String input) throws ParseException {
        // Create char array to hold unescaped char sequence
        char[] output = new char[input.length()];

        // The length of the output can be less than the input
        // due to discarded escape chars. This variable holds
        // the actual length of the output
        int length = 0;

        // We remember whether the last processed character was
        // an escape character
        boolean lastCharWasEscapeChar = false;

        // The multiplier the current unicode digit must be multiplied with.
        // E. g. the first digit must be multiplied with 16^3, the second with 16^2...
        int codePointMultiplier = 0;

        // Used to calculate the codepoint of the escaped unicode character
        int codePoint = 0;

        for (int i = 0; i < input.length(); i++) {
            char curChar = input.charAt(i);
            if (codePointMultiplier > 0) {
                codePoint += hexToInt(curChar) * codePointMultiplier;
                codePointMultiplier >>>= 4;
                if (codePointMultiplier == 0) {
                    output[length++] = (char) codePoint;
                    codePoint = 0;
                }
            } else if (lastCharWasEscapeChar) {
                if (curChar == 'u') {
                    // found an escaped unicode character
                    codePointMultiplier = 16 * 16 * 16;
                } else {
                    // this character was escaped
                    output[length] = curChar;
                    length++;
                }
                lastCharWasEscapeChar = false;
            } else {
                if (curChar == '\\') {
                    lastCharWasEscapeChar = true;
                } else {
                    output[length] = curChar;
                    length++;
                }
            }
        }

        if (codePointMultiplier > 0) {
            throw new ParseException("Truncated unicode escape sequence.");
        }

        if (lastCharWasEscapeChar) {
            throw new ParseException("Term can not end with escape character.");
        }

        return new String(output, 0, length);
    }

    /**
     * Returns the numeric value of the hexadecimal character
     */
    private static int hexToInt(char c) throws ParseException {
        if ('0' <= c && c <= '9') {
            return c - '0';
        } else if ('a' <= c && c <= 'f') {
            return c - 'a' + 10;
        } else if ('A' <= c && c <= 'F') {
            return c - 'A' + 10;
        } else {
            throw new ParseException("Non-hex character in Unicode escape sequence: " + c);
        }
    }
}
