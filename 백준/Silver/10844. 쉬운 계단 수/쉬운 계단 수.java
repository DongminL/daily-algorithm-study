import java.io.*;

public class Main {
    
    static int[][] stairs;    // 길이가 i이고, 0~9로 끝날 때 계단 수
    
    /* 길이가 N인 계단 수 구하기 */
    static void counting(int n) {
        long result = 0;    // 0~9로 끝나는 계단 수의 총합
        
        // 초기값 설정 (길이가 1일 때, 0은 올 수 없음)
        for (int i = 1; i < 10; i++) {
            stairs[1][i] = 1;
        }
        
        // 각 마지막 수의 계단 수는 직전단계의 계단 수로 구함
        for (int i = 2; i <= n; i++) {
            
            stairs[i][0] = stairs[i-1][1];    // 0으로 끝날 때, 앞에 1만 올 수 있음
            stairs[i][9] = stairs[i-1][8];    // 9로 끝날 때, 앞에 8만 올 수 있음
            for (int j = 1; j < 9; j++) {
                stairs[i][j] = (stairs[i-1][j-1] + stairs[i-1][j+1]) % 1000000000;
            }
        }
        
        // 0~9로 끝나는 계단 수의 합 구하기
        for (int i = 0; i < 10; i++) {
            result += (stairs[n][i] % 1000000000);
        }
        
        System.out.println(result % 1000000000);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        stairs = new int[n+1][10];
        
        counting(n);
    }
}