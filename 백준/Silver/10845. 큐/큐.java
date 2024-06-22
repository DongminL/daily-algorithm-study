import java.io.*;
import java.util.*;

public class Main {
    
    static Deque<Integer> queue = new ArrayDeque<>();
    static Integer result;
    
    static Integer operate(StringTokenizer command) {
        switch (command.nextToken()) {
            case "push" :
                queue.offerLast(Integer.parseInt(command.nextToken()));
                break;
            case "pop" :
                return queue.pollFirst();
            case "size" :
                return queue.size();
            case "empty" :
                return queue.isEmpty() ? 1 : 0;
            case "front" :
                return queue.peekFirst();
            case "back" :
                return queue.peekLast();
        }
        
        return -2;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            
            result = operate(st);
            
            if (result != null && result != -2) {
                sb.append(result).append("\n");
            } else if (result == null) {
                sb.append("-1").append("\n");
            }
        }
        
        System.out.println(sb);
    }
}