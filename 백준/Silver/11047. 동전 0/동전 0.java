import java.io.*;
import java.util.*;

public class Main {
    
    static void minimum(int n, int k, int[] coins) {
        int cnt = 0;
        int temp = k;
        
        for (int i = n-1; i >= 0; i--) {
            cnt += temp / coins[i];
            temp %= coins[i];
            
            if (temp == 0) {
                break;
            }
        }
        
        System.out.println(cnt);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coins = new int[n];
        
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        
        minimum(n, k, coins);
    }
}