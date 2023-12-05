import java.util.*;
import java.io.*;
 
public class WordStatInput {
    public static class StrStack {
        String[] str = new String[1];
        Map <String, Integer> mp = new HashMap<>();
        int ind = 0;
 
        public void add(String s) {
            str[ind] = s;
            ind++;
            if (ind == str.length) {
                str = Arrays.copyOf(str, ind * 2);
            }
        }
    }

    private static void read(String in, String out) throws IOException {
        FastScanner scan = new FastScanner(new InputStreamReader(new FileInputStream(in), "utf8")); 
        StrStack strs = new StrStack();
        try {
            while (scan.hasNextWord()) {
                String s = scan.nextWord().toLowerCase();
                if (strs.mp.get(s) == null) {
                    strs.mp.put(s, 1);
                    strs.add(s);
                }
                else {
                    strs.mp.put(s, strs.mp.get(s) + 1);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException!");
        } finally {
            scan.close();
        }
        BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(out), "utf8")
        );
        try {
            for (int i = 0; i < strs.ind; i++) {
                writer.write(strs.str[i] + ' ' + strs.mp.get(strs.str[i]) + "\n");
            }
        } finally {
            writer.close();
        }
    }
 
    public static void main(String[] args) {
        try {
            read(args[0], args[1]);
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException!");
        }
    }
}