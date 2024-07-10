import java.io.*;

public class Main {
    
    static int[] counting = new int[1001];    // 2Xi 크기의 직사각형을 채우는 경우의 수
    
    static void tiling(int n) {
        counting[1] = 1;
        counting[2] = 2;
        
        for (int i = 3; i <= n; i++) {
            counting[i] = (counting[i-1] + counting[i-2]) % 10007;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        tiling(n);
        
        System.out.println(counting[n]);
    }
}