import java.io.*;

public class Main {
    
    static long[] results;
    
    static void counting(int n) {
        if (n <= 2) {
            System.out.println(1);
            return;
        }
        
        results[1] = 1;
        results[2] = 1;
        
         for (int i = 3; i <= n; i++) {
            results[i] = results[i - 1] + results[i - 2];
        }
        
        System.out.println(results[n]);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        results = new long[n+1];
        
        counting(n);
    }
}