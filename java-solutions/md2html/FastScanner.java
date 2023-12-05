package md2html;

import java.util.*;
import java.io.*;

public class FastScanner {
    private static final int hyphon = 39;
    private Reader reader;
    private int startPosition = 0;
    private int finalPosition = 0;
    private Boolean hasData = true;
    private char[] buffer = new char[1];

    private void scan(int startPosition, int length) throws IOException {
        if (hasData) {
            int count = reader.read(buffer, startPosition, length);
            if (count == -1) {
                hasData = false;
            } else {
                finalPosition += count;
            }
        }
    }

    private void resize() throws IOException {
        buffer = Arrays.copyOf(Arrays.copyOfRange(buffer, startPosition, buffer.length), buffer.length * 2);
        finalPosition -= startPosition;
        startPosition = 0;
    }

    private Boolean isLineSeparator(char c) {
        return c == System.lineSeparator().charAt(0);
    }

    private void recalc(int val) throws IOException {
        scan(0, val);
        finalPosition = 0;
        startPosition = 0;
        scan(0, buffer.length);
    }

    public String nextLine() throws IOException {
        int finishIndex = finalPosition;
        int index = startPosition;
        while (true) {
            for (int i = index; i < finalPosition; i++) {
                if (isLineSeparator(buffer[i])) {
                    finishIndex = i;
                    break;
                }
            }
            if (hasData && finishIndex == finalPosition) {
                resize();
                index = finalPosition;
                scan(finalPosition, buffer.length - finalPosition);
                finishIndex = finalPosition;
            } else {
                break;
            }
        }
        String result = new String(buffer, startPosition, finishIndex - startPosition);
        finishIndex += System.lineSeparator().length();
        if (finishIndex >= finalPosition) {
            recalc(finishIndex - finalPosition);
        } else {
            startPosition = finishIndex;
        }
        return result;
    }

    public Boolean hasNextLine() throws IOException {
        return finalPosition != 0;
    }

    private Boolean checker(int value, char ch) {
        if (value == 1) {
            return Character.isWhitespace(ch);
        } else {
            return Character.getType(ch) != Character.DASH_PUNCTUATION 
            && !Character.isLetter(ch) && ch != hyphon;
        }
    }

    public Boolean hasNext() throws IOException {
        return hasNextElement(1);
    }

    public Boolean hasNextWord() throws IOException {
        return hasNextElement(2);
    }

    private Boolean hasNextElement(int value) throws IOException {
        int index = startPosition;
        while (true) {
            for (int i = index; i < finalPosition; i++) {
                if (!checker(value, buffer[i])) {
                    return true;
                }
            }
            if (hasData) {
                resize();
                index = finalPosition;
                scan(finalPosition, buffer.length - finalPosition);
            } else {
                return false;
            }
        } 
    }

    private int findIndex(int value) throws IOException {
        int index = startPosition;
        while (true) {
            for (int i = index; i < finalPosition; i++) {
                if (!checker(value, buffer[i])) {
                    return i;
                }
            }
            if (hasData) {
                resize();
                index = finalPosition;
                scan(finalPosition, buffer.length - finalPosition);
            } else {
                return -1;
            }
        }
    }

    public String next() throws IOException {
        return nextElement(1);
    }

    public String nextWord() throws IOException {
        return nextElement(2);
    }

    private String nextElement(int value) throws IOException {
        int startIndex = findIndex(value);
        if (startIndex != -1) {
            int index = startIndex + 1;
            int finishIndex = finalPosition;
            while (true) {
                for (int i = index; i < finalPosition; i++) {
                    if (checker(value, buffer[i])) {
                        finishIndex = i;
                        break;
                    }
                }
                if (hasData && finishIndex == finalPosition) {
                    startIndex -= startPosition;
                    resize();
                    index = finalPosition;
                    scan(finalPosition, buffer.length - finalPosition);
                    finishIndex = finalPosition;
                } else {
                    break;
                }
            }
            String result = new String(buffer, startIndex, finishIndex - startIndex);
            if (finishIndex < finalPosition && isLineSeparator(buffer[finishIndex])) {
                finishIndex += System.lineSeparator().length();
                if (finishIndex >= finalPosition) {
                    buffer = Arrays.copyOf(buffer, buffer.length * 2);
                    scan(finalPosition, buffer.length - finalPosition);
                }
            }
            if (finishIndex >= finalPosition) {
                recalc(finishIndex - finalPosition);
            } else {
                startPosition = finishIndex;
            }
            return result;
        } else {
            return "";
        }
    }

    public Boolean checkLineSeparator() throws IOException {
        int index = startPosition;
        while (true) {
            for (int i = index; i < finalPosition; i++) {
                if (isLineSeparator(buffer[i])) {
                    return true;
                }
                if (!checker(2, buffer[i])) {
                    return false;
                }
            }
            if (hasData) {
                resize();
                index = finalPosition;
                scan(finalPosition, buffer.length - finalPosition);
            } else {
                return false;
            }
        } 
    }

    public FastScanner(InputStream stream) throws IOException {
        this.reader = new InputStreamReader(stream);
        scan(0, 1);
    }

    public FastScanner(Reader reader) throws IOException {
        this.reader = reader;
        scan(0, 1);
    } 

    public void close() throws IOException {
        reader.close();
    }
}