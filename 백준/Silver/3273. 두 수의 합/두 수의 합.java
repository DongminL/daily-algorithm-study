import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(br.readLine());
        
        int[] values = new int[n];
        
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(values);
        
        int start = 0;
        int end = n-1;
        int cnt = 0;
        int sum = 0;
        
        while (start < end) {
            sum = values[start] + values[end];
            
            if (sum == x) {
                cnt++;
                start++;
                end--;
            } else if (sum < x) {
                start++;
            } else {
                end--;
                
                if (values[end] <= x/2.0) {
                    break;
                }
            }
        }
        
        System.out.println(cnt);
    }
}