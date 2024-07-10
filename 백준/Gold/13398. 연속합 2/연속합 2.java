import java.io.*;
import java.util.*;

public class Main {
    
    static int[] arr;    // 입력 받은 수열
    static int[] rightResults;    // 마지막 값이 오른쪽에 있는 연속합의 결과값
    static int[] leftResults;    // 마지막 값이 왼쪽에 있는 연속합의 결과값

    /* 연속합의 최대값 구하기 */
    static int sumMax(int n) {
        // 초기값 설정
        rightResults = new int[n];
        rightResults[0] = arr[0];
        leftResults = new int[n];
        leftResults[n-1] = arr[n-1];
        int result = rightResults[0]; // 가장 큰 연속합

        // 마지막 값이 오른쪽에 있는 연속합을 구함 (DP)
        for (int i = 1; i < n; i++) {
            // 연속합의 값과 현재 값 중 큰 값만 저장
            rightResults[i] = Math.max(rightResults[i-1] + arr[i], arr[i]);
            
            result = Math.max(rightResults[i], result);    // 하나도 제거하지 않았을 때, 연속합의 최대값
        }

        // 마지막 값이 왼쪽에 있는 연속합을 구함 (DP)
        for (int i = n-2; i >= 0; i--) {
            // 연속합의 값과 현재 값 중 큰 값만 저장
            leftResults[i] = Math.max(leftResults[i+1] + arr[i], arr[i]);
        }

        // i번째 숫자를 제외한 연속합의 최대값 구하기
        for (int i = 1; i < n-1; i++) {
            result = Math.max(rightResults[i-1] + leftResults[i+1], result);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(sumMax(n));
    }
}