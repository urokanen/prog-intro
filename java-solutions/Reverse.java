import java.util.*;
import java.io.*;

public class Reverse {
    public static void main(String[] args) {
        try {
            FastScanner scan = new FastScanner(System.in);
            Stack<String> st = new Stack<>(); 
            Stack<Integer> lens = new Stack<>();
            while (scan.hasNextLine()) {
                InputStream stream = new ByteArrayInputStream(scan.nextLine().getBytes());
                FastScanner elements = new FastScanner(stream);
                int cnt = 0;
                while (elements.hasNext()) {
                    st.push(elements.next());
                    cnt++;
                }   
                lens.push(cnt);
                elements.close();
            }
            scan.nextLine();
            scan.close();
            while (!lens.empty()) {
                for (int i = 0; i < lens.peek(); i++) {
                    System.out.print(st.pop());
                    System.out.print(" ");
                }
                System.out.println();
                lens.pop();
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}