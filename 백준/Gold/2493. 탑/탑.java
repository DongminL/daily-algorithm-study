import java.io.*;
import java.util.*;

class Top {
    int height;
    int number;
    
    public Top(int height, int number) {
        this.height = height;
        this.number = number;
    }
}

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Top> stack = new Stack<>();
        
        int n = Integer.parseInt(br.readLine());
        String[] tops = br.readLine().split(" ");
        
        stack.push(new Top(Integer.parseInt(tops[0]), 1));
        sb.append("0");
        
        Top cur;
        for(int i = 1; i < n; i++) {
            cur = new Top(Integer.parseInt(tops[i]), i+1);
            
            while(!stack.isEmpty()) {
                if (cur.height <= stack.peek().height) {
                    sb.append(" ").append(stack.peek().number);
                    break;
                } else {
                    stack.pop();
                }
            }
            
            if (stack.isEmpty()) {
                sb.append(" ").append("0");
            }

            stack.push(cur);
        }
        
        System.out.println(sb);
    }
}