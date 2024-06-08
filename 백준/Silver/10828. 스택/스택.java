import java.io.*;
import java.util.*;

public class Main {
    
    static Stack<Integer> stack = new Stack<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            
            int result = operation(st);
            if (result != -2) {
                sb.append(result).append("\n");
            }
        }
        
        System.out.println(sb);
    }
    
    public static int operation(StringTokenizer command) {
        try {
            switch(command.nextToken()) {
            case "push" :
                stack.push(Integer.parseInt(command.nextToken()));
                break;
            case "pop" :
                return stack.pop();
            case "size" :
                return stack.size();
            case "empty" :
                return stack.isEmpty() ? 1 : 0;
            case "top" :
                return stack.peek();
            }
        } catch(EmptyStackException e) {
            return -1;
        }
        
        return -2;
    }
}