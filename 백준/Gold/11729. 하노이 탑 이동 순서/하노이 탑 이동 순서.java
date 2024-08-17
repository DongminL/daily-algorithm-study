import java.io.*;
import java.util.*;

public class Main {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder result = new StringBuilder();    // 최종 출력 결과
    
    int n;    // 원판의 개수
    
    /* 
        하노이의 탑
        start: 출발지, mid: 원판을 옮기기 위한 중간 다리 역할, end: 도착지
        초기값 -> start: 1, mid: 2, end: 3
    */
    private void hanoi(int n, int start, int mid, int end) {
        // 1개의 원판만 옮길 때
        if (n == 1) {
            result.append(start + " " + end + "\n");
            return;
        }
        
        // 맨 밑에 원판을 위해 n-1개의 원판을 1 -> 2로 옮길 때
        hanoi(n-1, start, end, mid);
        
        // 맨 밑에 원판을 A -> C로 옮길 때
        result.append(start + " " + end + "\n");
        
        // n-1개의 원판을 2 -> 3으로 옮길 때
        hanoi(n-1, mid, start, end);
    }
    
    private void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        
        // 옮긴 횟수
        result.append(((int) (Math.pow(2, n) - 1)) + "\n");
        
        // 초기값 -> start: 1, mid: 2, end: 3
        hanoi(n, 1, 2, 3);    
        
        System.out.println(result);
    }
    
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}