import java.io.*;
import java.util.*;

public class Main {
    
    static LinkedList<Integer> deque = new LinkedList<>();
    static int count = 0;

    static void right(int k) {
        while(deque.peek() != k) {
            deque.offerFirst(deque.pollLast());
            count++;
        }

        deque.pollFirst();
    }

    static void left(int k) {
        while(deque.peek() != k) {
            deque.offerLast(deque.pollFirst());
            count++;
        }

        deque.pollFirst();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            deque.offer(i);
        }

        st = new StringTokenizer(br.readLine());


        for (int i = 0; i < m; i++) {
            int k = Integer.parseInt(st.nextToken());
            int index = deque.indexOf(k);

            if (index <= Math.floor(deque.size() / 2.0)) {
                left(k);
            } else {
                right(k);
            }
        }

        System.out.println(count);
    }
}