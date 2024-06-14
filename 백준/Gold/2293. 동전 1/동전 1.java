import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int coin;
        int[] cases = new int[k+1];
        cases[0] = 1;
        
        for (int i = 0; i < n; i++) {
            coin = Integer.parseInt(br.readLine());
            
            for (int j = coin; j <= k; j++) {
                cases[j] += cases[j - coin];
            }
        }
        
        System.out.println(cases[k]);
    }
}