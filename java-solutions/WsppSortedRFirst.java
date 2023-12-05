import java.util.*;
import java.io.*;
 
public class WsppSortedRFirst {
    public static class IntList {
        int[] mas = new int[]{0};
        int ind = 0;

        public void add(int val) {
            mas[ind] = val;
            ind++;
            if (ind == mas.length) {
                mas = Arrays.copyOf(mas, ind * 2);
            }
        }
    }

    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static class Triplet implements Comparable<Triplet> {
        String s;
        int count;
        IntList arr;
 
        public Triplet(String s, int count, IntList arr) {
            this.s = s;
            this.count = count;
            this.arr = arr;
        }
 
        public int compareTo(Triplet compared) {
            return this.s.compareTo(compared.s);
        }
    }

    private static void read(String in, String out) throws IOException {
        FastScanner scan = new FastScanner(new InputStreamReader(new FileInputStream(in), "utf8")); 
        Map <String, Integer> mp = new HashMap<>();
        ArrayList <Integer> line = new ArrayList<>();
        ArrayList <String> strings = new ArrayList<>();
        ArrayList <Integer> times = new ArrayList<>();
        ArrayList <IntList> arr = new ArrayList<>();
        int count = 0;
        int countLines = 1;
        try {
            int words = 0;
            while (scan.hasNextWord()) {
                String s = scan.nextWord().toLowerCase();
                words++;
                if (mp.get(s) == null) {
                    arr.add(new IntList());
                    strings.add(s);
                    times.add(0);
                    mp.put(s, count);
                    line.add(countLines - 1);
                    count++;
                }
                int ind = mp.get(s);
                if (line.get(ind) != countLines) {
                    arr.get(ind).add(words);
                    line.set(ind, countLines);
                }
                times.set(ind, times.get(ind) + 1);
                if (scan.checkLineSeparator()) {
                    countLines++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException!");
        } finally {
            scan.close();
        }
        ArrayList <Triplet> words = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            words.add(new Triplet(reverse(strings.get(i)), times.get(i), arr.get(i)));
        }
        Collections.sort(words);
        BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(out), "utf8")
        );
        try {
            for (int i = 0; i < count; i++) {
                writer.write(reverse(words.get(i).s) + ' ' + words.get(i).count);
                for (int j = 0; j < words.get(i).arr.ind; j++) {
                    writer.write(" ");
                    writer.write(Integer.toString(words.get(i).arr.mas[j]));
                }
                writer.write("\n");
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