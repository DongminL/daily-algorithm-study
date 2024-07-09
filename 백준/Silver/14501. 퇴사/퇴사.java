import java.io.*;
import java.util.*;

public class Main {
    
    static int[][] schedule;        // 상담 일정
    static int[] accrued;    // 시작일로부터 최대 수익
    
    /* 최대 수익 구하기 */
    static void maximum(int n) {
        for (int i = n; i > 0; i--) {
            if (i + schedule[0][i] > n + 1) {
                accrued[i] = accrued[i+1];
            } else {
                accrued[i] = Math.max(accrued[i+1], 
                                      accrued[i + schedule[0][i]] + schedule[1][i]);
            }
        }
        
        System.out.println(accrued[1]);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        schedule = new int[2][n+1];
        accrued = new int[n+2];
        
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            
            schedule[0][i] = Integer.parseInt(st.nextToken());    // 상담을 완료하는데 걸리는 시간
            schedule[1][i] = Integer.parseInt(st.nextToken());    // 당일 상담의 수익
        }
        
        maximum(n);
    }
}