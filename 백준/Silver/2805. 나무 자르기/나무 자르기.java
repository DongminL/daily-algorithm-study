import java.io.*;
import java.util.*;

public class Main {
    
    static void cutting(int n, int m, int[] trees, int max) {
        int min = 0;
        int mid = 0;
        
        while (min <= max) {
            mid = (min + max) / 2;
            long sum = 0;
            
            for (int i = 0; i < n; i++) {
                if (mid < trees[i]) {
                    sum += (trees[i] - mid);
                }
            }
            if (sum < m) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        
        System.out.println(max);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int[] trees = new int[n];
        int max = 0;
        
        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            
            if (max < trees[i]) {
                max = trees[i];
            }
        }
        
        cutting(n, m, trees, max);
    }
}