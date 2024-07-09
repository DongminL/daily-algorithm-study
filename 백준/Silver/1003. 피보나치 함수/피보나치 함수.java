import java.io.*;

public class Main {
    
    static int[][] results = new int[41][2];
    
    static int[] fibo(int n) {
        if (n > 1 && results[n][0] == 0 && results[n][1] == 0) {
            results[n][0] = fibo(n-1)[0] + fibo(n-2)[0];
            results[n][1] = fibo(n-1)[1] + fibo(n-2)[1];
        }
        
        return results[n];
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine());
        
        results[0][0] = 1;
        results[0][1] = 0;
        results[1][0] = 0;
        results[1][1] = 1;
        
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            
            fibo(n);
            
            sb.append(results[n][0]).append(" ").append(results[n][1]).append("\n");
        }
        
        System.out.println(sb);
    }
}