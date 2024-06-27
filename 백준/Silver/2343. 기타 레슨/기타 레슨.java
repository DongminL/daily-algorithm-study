import java.io.*;
import java.util.*;

public class Main {
    
    static void bluray(int n, int m, int[] length, StringTokenizer st) {
        int min = 0;
        int mid = 0;
        int max = 0;
        
        for (int i = 0; i < n; i++) {
            length[i] = Integer.parseInt(st.nextToken());
            
            if (min < length[i]) {
                min = length[i];
            }
            
            max += length[i];
        }
        
        while (min <= max) {
            int cnt = 1;
            int sum = 0;
            mid = (min + max) / 2;
            
            for (int i = 0; i < n; i++) {
                if (sum + length[i] > mid) {
                    cnt++;
                    sum = 0;
                }
                
                sum += length[i];
            }
            
            if (cnt > m) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        
        System.out.println(min);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] length = new int[n];
        
        st = new StringTokenizer(br.readLine());
        
        bluray(n, m, length, st);
    }
}