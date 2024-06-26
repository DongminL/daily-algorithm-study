import java.io.*;
import java.util.*;

public class Main {
    
    static long multiple(int a, int exp, int mod) {
        if (a == 1) {
            return 1;
        }
        
        if (exp == 1) {
            return a % mod;
        }
        
        long temp = multiple(a, exp/2, mod);
        
        if (exp % 2 == 1) {
            return (temp * temp % mod) * a % mod;
        } else {
            return temp * temp % mod;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int a = Integer.parseInt(st.nextToken());
        int exp = Integer.parseInt(st.nextToken());
        int mod = Integer.parseInt(st.nextToken());
        
        System.out.println(multiple(a, exp, mod));
    }
}