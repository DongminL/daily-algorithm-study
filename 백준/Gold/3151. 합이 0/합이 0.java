import java.io.*;
import java.util.*;

public class Main {
    
    static void teamChoice(int n, int[] level) {
        long cnt = 0;
        
        for (int i = 0; i < n; i++) {
            if (level[i] > 0) {
                break;
            }
            
            int start = i+1;
            int end = n-1;
            
            while (start < end) {
                int sum = level[i] + level[start] + level[end];
                
                if (sum == 0) {
                    int s = 1;
                    int e = 1;
                    
                    if (level[start] == level[end]) {
                        cnt += combin(end - start + 1);
                        
                        break;
                    }
                    
                    while (level[start] == level[start+1]) {
                        s++;
                        start++;
                    }
                    
                    while (level[end] == level[end-1]) {
                        e++;
                        end--;
                    }
                    
                    cnt += s * e;
                }
                
                if (sum < 0) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        
        System.out.println(cnt);
    }
    
    static int combin(int n) {
        return n * (n-1) / 2;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        int[] level = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            level[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(level);
        
        teamChoice(n, level);
    }
}