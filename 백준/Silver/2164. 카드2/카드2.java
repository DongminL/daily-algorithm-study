import java.io.*;
import java.util.*;

public class Main {
    
    static Deque<Integer> queue = new ArrayDeque<>();
    
    static int card(int n) {
        for (int i = 1; i <= n; i++) {
            queue.offerLast(i);
        }
        
        while (queue.size() > 1) {
            queue.pollFirst();
            
            queue.offerLast(queue.pollFirst());
        }
        
        return queue.peek();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        int result = card(n);
        
        System.out.println(result);
    }
}