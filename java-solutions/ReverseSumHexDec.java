import java.util.*;

public class ReverseSumHexDec {
    public static int get(String s) {
        if (s.length() > 1 && (s.charAt(0) == '0')) {
            return Integer.parseUnsignedInt(s.substring(2), 16) - (1 << 32) + 1;
        }
        return Integer.parseInt(s);
    }

    public static class nums {
        int[] arr = new int[1];

        public void resize(int ind) {
            while (ind >= arr.length) {
                arr = Arrays.copyOf(arr, arr.length * 2);
            }
        }

        public void update(int value, int ind) {
            resize(ind);
            arr[ind] += value;
        }

        public int get(int ind) {
            resize(ind);
            return arr[ind];
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        nums add = new nums();
        while (scan.hasNextLine()) {
            Scanner elements = new Scanner(scan.nextLine());
            int pref_sum = 0;
            int cnt = 0;
            while (elements.hasNext()) {
                int elem = get(elements.next());
                pref_sum += add.get(cnt) + elem; 
                System.out.print(pref_sum);
                System.out.print(' ');
                add.update(elem, cnt);
                cnt++;
            }   
            System.out.println();
        }
    }
}