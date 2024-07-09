import java.io.*;

public class Main {
    
    static int[] results;
    
    static void makeOne(int n) {
        for (int i = 2; i <= n; i++) {
            results[i] = results[i-1] + 1;
            
            if (i % 3 == 0) {
                results[i] = Math.min(results[i], results[i/3] + 1);
            } 
            
            if (i % 2 == 0) {
                results[i] = Math.min(results[i], results[i/2] + 1);
            }
        } 
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        results = new int[n+1];
        results[1] = 0;
        
        makeOne(n);
        
        System.out.println(results[n]);
    }
}