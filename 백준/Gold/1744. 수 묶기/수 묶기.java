import java.io.*;
import java.util.*;

public class Main {
    
    static void maximum(int zeroCnt, int oneCnt, PriorityQueue<Integer> plus, PriorityQueue<Integer> minus) {
        int sum = oneCnt;
        int n = plus.size();

        for (int i = 1; i <= n-1; i+=2) {
            int num1 = plus.poll();
            int num2 = plus.poll();

            sum += (num1 * num2);
        }

        n = minus.size();
        for (int i = 1; i <= n-1; i+=2) {
            int num1 = minus.poll();
            int num2 = minus.poll();

            sum += (num1 * num2);
        }

        if (!plus.isEmpty()) {
            sum += plus.poll();
        }
        
        if (!minus.isEmpty() && zeroCnt == 0) {
            sum += minus.poll();
        }

        System.out.println(sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> plus = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minus = new PriorityQueue<>();
        int zeroCnt = 0;
        int oneCnt = 0;

        for (int i = 0; i < n; i++) {
            int e = Integer.parseInt(br.readLine());
            if (e > 1) {
                plus.add(e);
            } else if (e < 0) {
                minus.add(e);
            } else if (e == 0) {
                zeroCnt++;
            } else {
                oneCnt++;
            }
        }

        maximum(zeroCnt, oneCnt, plus, minus);
    }
}