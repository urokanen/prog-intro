package md2html;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class Md2Html {
    private static class Element {
        public String open, close, tag;
        Element (String open, String close, String tag) {
            this.open = open;
            this.close = close;
            this.tag = tag;
        }
    }

    private static int check(StringBuilder s, int pos, int startPos, String elem) {
        if (elem.length() == 1) {
            if ((pos == startPos || (s.charAt(pos - 1) != elem.charAt(0) && s.charAt(pos - 1) != '\\')) && s.charAt(pos) == elem.charAt(0) &&
                    (pos + 1 == s.length() || s.charAt(pos + 1) != elem.charAt(0))) {
                return 1;
            }
        } else {
            if (pos + 1 < s.length() && (pos == startPos || (s.charAt(pos - 1) != elem.charAt(1) && s.charAt(pos - 1) != '\\')) &&
                    s.charAt(pos) == elem.charAt(0) && s.charAt(pos + 1) == elem.charAt(1) && (pos + 2 == s.length() || s.charAt(pos + 2) != elem.charAt(0))) {
                return 1;
            }
        }
        return 0;
    }

    private static List <Element> marks = new ArrayList<>();
    private static char[] special = new char[]{'<', '>', '&'};
    private static final String[] SPECIAL_TAGS = new String[]{"&lt;", "&gt;", "&amp;"};

    static {
        marks.add(new Element("`", "`", "code"));
        marks.add(new Element("*", "*", "em"));
        marks.add(new Element("_", "_", "em"));
        marks.add(new Element("**", "**", "strong"));
        marks.add(new Element("__", "__", "strong"));
        marks.add(new Element("--", "--", "s"));
        marks.add(new Element("<<", ">>", "ins"));
        marks.add(new Element("}}", "{{", "del"));
    }

    private static void add(StringBuilder s, String elem, int count, boolean isFinish) {
        s.append('<');
        if (isFinish) {
            s.append('/');
        }
        s.append(elem);
        if (elem.equals("h")) {
            s.append(count);
        }
        s.append('>');
    }

    private static boolean checkSpecial(StringBuilder s, StringBuilder ans, int ind) {
        for (int j = 0; j < special.length; j++) {
            if (s.charAt(ind) == special[j]) {
                ans.append(SPECIAL_TAGS[j]);
                return true;
            }
        }
        return false;
    }

    private static void count(int[] counts, StringBuilder s, int ind) {
        for (int i = ind; i < s.length(); i++) {
            for (int j = 0; j < marks.size(); j++) {
                counts[j] += check(s, i, ind, marks.get(j).close);
            }
        }
    }

    private static void calcAns(StringBuilder s, StringBuilder ans) {
        int count = 0;
        int ind = 0;
        String symbol = "p";
        if (s.charAt(0) == '#') {
            count++;
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) != '#') {
                    if (s.charAt(i) == ' ') {
                        symbol = "h";
                        ind += count + 1;
                    }
                    break;
                } else {
                    count++;
                }
            }
        }
        add(ans, symbol, count, false);
        int[] counts = new int[marks.size()];
        count(counts, s, ind);
        List<String> stack = new ArrayList<>();
        int start = ind;
        while (ind < s.length()) {
            boolean flag = false;
            for (int j = 0; j < marks.size(); j++) {
                if (!flag && check(s, ind, start, marks.get(j).close) == 1 && !stack.isEmpty() && stack.get(stack.size() - 1).equals(marks.get(j).open)) {
                    add(ans, marks.get(j).tag, count, true);
                    stack.remove(stack.size() - 1);
                    counts[j] -= (j < 6) ? 2 : 1;
                    ind += marks.get(j).close.length();
                    flag = true;
                } else if (!flag && check(s, ind, start, marks.get(j).open) == 1 && ((counts[j] > 1 && j < 6) || (counts[j] > 0 && j > 5))) {
                    add(ans, marks.get(j).tag, count, false);
                    stack.add(marks.get(j).open);
                    ind += marks.get(j).open.length();
                    flag = true;
                }
            }
            if (!flag && checkSpecial(s, ans, ind)) {
                ind++;
                flag = true;
            }
            if (!flag) {
                if (s.charAt(ind) != '\\') {
                    ans.append(s.charAt(ind));
                }
                ind++;
            }
        }
        add(ans, symbol, count, true);
        ans.append(System.lineSeparator());
    }

    private static void solve(String in, String out) throws IOException {
        FastScanner scan = new FastScanner(new InputStreamReader(new FileInputStream(in), StandardCharsets.UTF_8));
        StringBuilder ans = new StringBuilder();
        try {
            while (scan.hasNextLine()) {
                StringBuilder s = new StringBuilder();
                while (true) {
                    String newS = scan.nextLine();
                    if (!newS.isEmpty()) {
                        if (!s.isEmpty()) {
                            s.append(System.lineSeparator());
                        }
                        s.append(newS);
                    } else {
                        break;
                    }
                }
                if (!s.isEmpty()) {
                    calcAns(s, ans);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException!");
        } finally {
            scan.close();
        }
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(out), StandardCharsets.UTF_8));
        try {
            writer.write(ans.toString());
        } finally {
            writer.close();
        }
    }

    public static void main(String[] args) {
        try {
            if (args.length == 2) {
                solve(args[0], args[1]);
            } else {
                System.out.println("incorrect arguments");
            }
        } catch (IOException e) {
            System.out.println("IOException!");
        }
    }
}
