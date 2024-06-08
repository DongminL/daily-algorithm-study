import java.io.*;
import java.util.*;

public class Main {
    
    static Stack<Integer> stack = new Stack<>();
    static StringBuilder sb = new StringBuilder();
    static int pre = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int k;
        boolean flag = false;
        for(int i = 0; i < n; i++) {
            k = Integer.parseInt(br.readLine());

            if (pre < k) {
                push(pre, k - pre);
                pop();
            } else if (stack.peek() > k) {
                System.out.println("NO");
                return;
            } else {
                pop();
            }
        }

        System.out.println(sb);
    }

    public static void push(int cur, int cnt) {
        for (int i = cur+1; i <= cur+cnt; i++) {
            stack.push(i);
            sb.append("+\n");
        }

        pre = cur + cnt;
    }

    public static void pop() {
        stack.pop();
        sb.append("-\n");
    }
}