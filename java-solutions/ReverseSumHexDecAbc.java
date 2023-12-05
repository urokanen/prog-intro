import java.util.*;
import java.io.*;

public class ReverseSumHexDecAbc {
    public static int get(String s) {
        s = s.toLowerCase();
        if (s.length() > 2 && s.charAt(1) == 'x') {
            return Integer.parseUnsignedInt(s.substring(2), 16) - (1 << 32) + 1;
        }
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if ('a' <= arr[i] && arr[i] <= 'z') {
                arr[i] = (char)(arr[i] - 'a' + '0');
            }
        }
        return Integer.parseInt(String.valueOf(arr));
    }

    public static String convertToAbc(int value) {
        char[] arr = Integer.toString(value).toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if ('0' <= arr[i] && arr[i] <= '9') {
                arr[i] = (char)(arr[i] - '0' + 'a');
            }
        }
        return String.valueOf(arr);
    }

    public static void main(String[] args) {
        try{
            FastScanner scan = new FastScanner(System.in);
            ArrayList <Integer> arr = new ArrayList<>();
            while (scan.hasNextLine()) {
                InputStream stream = new ByteArrayInputStream(scan.nextLine().getBytes());
                FastScanner elements = new FastScanner(stream);
                int pref_sum = 0;
                int cnt = 0;
                while (elements.hasNext()) {
                    if (arr.size() == cnt) {
                        arr.add(0);
                    }
                    int elem = get(elements.next());
                    int val = arr.get(cnt);
                    pref_sum += val + elem;
                    System.out.print(convertToAbc(pref_sum));
                    System.out.print(' ');
                    arr.set(cnt, val + elem);
                    cnt++;
                }   
                elements.close();
                System.out.println();
            }
            scan.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        }  
    }
}