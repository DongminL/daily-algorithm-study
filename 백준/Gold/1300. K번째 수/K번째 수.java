import java.io.*;
import java.util.*;

public class Main {
    
    static void search(int n, int k) {
        long result = 0;
        int start = 1;
        int end = k;
        
        while (start <= end) {
            int mid = (start + end) / 2;
            long cnt = 0;
            
            for (int i = 1; i <= n; i++) {
                cnt += Math.min(mid / i, n);
            }
            
            if (cnt < k) {
                start = mid + 1;
            } else {
                end = mid - 1;
                result = mid;
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        search(n, k);
    }
}