import java.util.*;
import java.io.*;
 
public class Wspp {
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

    private static void read(String in, String out) throws IOException {
        FastScanner scan = new FastScanner(new InputStreamReader(new FileInputStream(in), "utf8")); 
        Map <String, Integer> mp = new HashMap<>();
        String[] strings = new String[1];
        IntList[] arr = new IntList[1];
        int count = 0;
        int words = 0;
        try {
            while (scan.hasNextWord()) {
                String s = scan.nextWord().toLowerCase();
                words++;
                if (mp.get(s) == null) {
                    arr[count] = new IntList();
                    strings[count] = s;
                    mp.put(s, count);
                    count++;
                    if (count == arr.length) {
                        strings = Arrays.copyOf(strings, count * 2);
                        arr = Arrays.copyOf(arr, count * 2);
                    } 
                }
                arr[mp.get(s)].add(words);
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
            for (int i = 0; i < count; i++) {
                writer.write(strings[i] + ' ' + arr[i].ind);
                for (int j = 0; j < arr[i].ind; j++) {
                    writer.write(" ");
                    writer.write(Integer.toString(arr[i].mas[j]));
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