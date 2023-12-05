import java.util.*;
import java.io.*;
 
public class WordStatCountAffixL {
    public static class StringStack {
        String[] stringsArray = new String[1];
        Map <String, Integer> mp = new HashMap<>();
        int ind = 0;
 
        public void add(String s) {
            stringsArray[ind] = s;
            ind++;
            if (ind == stringsArray.length) {
                stringsArray = Arrays.copyOf(stringsArray, ind * 2);
            }
        }
    }
 
    public static class Triplet implements Comparable<Triplet> {
        int cnt;
        int pos;
        String s;
 
        public Triplet(int cnt, int pos, String s) {
            this.cnt = cnt;
            this.pos = pos;
            this.s = s;
        }
 
        public int compareTo(Triplet compared) {
            int res = Integer.compare(this.cnt, compared.cnt);
            if (res == 0) {
                res = Integer.compare(this.pos, compared.pos);
            }
            return res;
        }
    }
 
    private static boolean check(char ch) {
        if (Character.getType(ch) == Character.DASH_PUNCTUATION 
            || Character.isLetter(ch) || ch == 39) {
            return true;
        }
        return false;
    }
 
    private static void read(String in, String out) throws IOException {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(new FileInputStream(in), "utf8"));
        StringStack strings = new StringStack();
        try {
            StringBuilder pref = new StringBuilder();
            char predLastPosition = ' ';
            char lastPosition = ' ';
            while (true) {
                int element = reader.read();
                char newChar = ' ';
                if (element != -1) {
                    newChar = (char) element;
                }
                if (element != -1 && check(newChar)) {
                    if (pref.length() < 2) {
                        pref.append(newChar);
                    }
                    predLastPosition = lastPosition;
                    lastPosition = newChar;
                } else if (pref.length() >= 2) {
                    List <String> affixes = new ArrayList<>();
                    affixes.add(pref.toString().toLowerCase());
                    StringBuilder suf = new StringBuilder();
                    suf.append(predLastPosition);
                    suf.append(lastPosition);
                    affixes.add(suf.toString().toLowerCase());
                    for (int j = 0; j < 2; j++) {
                        if (strings.mp.get(affixes.get(j)) == null) {
                            strings.mp.put(affixes.get(j), 1);
                            strings.add(affixes.get(j));
                        }
                        else {
                            strings.mp.put(affixes.get(j), strings.mp.get(affixes.get(j)) + 1);
                        }
                    }
                    pref.setLength(0);
                } else {
                    pref.setLength(0);
                }
                if (element == -1) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException!");
        } finally {
            reader.close();
        }
        List <Triplet> affixes = new ArrayList<>();
        for (int i = 0; i < strings.ind; i++) {
            Triplet affix = new Triplet(strings.mp.get(strings.stringsArray[i]), i, strings.stringsArray[i]);
            affixes.add(affix);
        }
        Collections.sort(affixes);
        BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(out), "utf8"));
        try {
            for (int i = 0; i < strings.ind; i++) {
                writer.write(affixes.get(i).s + ' ' + affixes.get(i).cnt + System.lineSeparator());
            }
        } finally {
            writer.close();
        }
    }
 
    public static void main(String[] args) {
        try {
            if (args.length == 2) {
                read(args[0], args[1]);
            } else {
                System.out.println("incorrect arguments");
            }
        } catch (IOException e) {
            System.out.println("IOException!");
        } 
    }
}
