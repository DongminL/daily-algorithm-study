import java.io.*;
import java.util.*;

public class Main {
    
    static int n;    // N자리 수
    static StringBuilder result = new StringBuilder();    // N자리의 신기한 소수들
    
    /* 신기한 소수 찾기 (cnt 자리의 숫자 num, DFS로 구현하였기 때문에 오름차순 정렬이 됨) */
    static void searchPrimeNum(int num, int cnt) {
        // N자리 수를 만족하면 DFS 종료
        if (cnt == n) {
            // 소수 발견 시 결과값에 추가
            if (checkPrimeNum(num)) {
                result.append(num).append("\n");
            }
            
            return;    // DFS 종료
        }
        
        // 마지막 자리에 홀수만 대입 (짝수가 오면 무조건 소수가 아니기 때문)
        for (int i = 1; i < 10; i+=2) {
            if (checkPrimeNum(10*num + i)) {
                searchPrimeNum(10*num + i, cnt+1);    // 다음 자리수로 넘어가기
            }
        }
    }
    
    /* 소수 판별 (true: 소수 O, false: 소수 X) */
    static boolean checkPrimeNum(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());    // N자리 수
        
        // 가장 왼쪽 수부터 소수를 만족해야 함 (1은 소수가 아니기에 제외)
        searchPrimeNum(2, 1);
        searchPrimeNum(3, 1);
        searchPrimeNum(5, 1);
        searchPrimeNum(7, 1);
        
        System.out.println(result);    // N자리의 신기한 소수들 출력
    }
}