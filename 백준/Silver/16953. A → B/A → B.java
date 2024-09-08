import java.io.*;
import java.util.*;

class Solution {

    int answer; // A를 B로 바꾸는데 필요한 연산의 최솟값에 1을 더한 값

    public int solution(int num, int target) {
        answer = Integer.MAX_VALUE;

        // num -> target으로 변환
        changeCount(num, target, 0);

        // 초기값과 같으면 -1 처리 (못찾았기 때문)
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    /* num -> target이 될 때 연산 횟수 구하기 (DFS) */
    private void changeCount(long num, int target, int count) {
        // 정해준 범위를 넘을 때
        if (num > 1000000000) {
            return;
        }

        // 찾는 것을 종료할 때
        if (num >= target) {
             answer = num == target ? Math.min(answer, count + 1) : answer;
             return;
        }

        changeCount(num * 2, target, count + 1);    // 2 곱하는 연산
        changeCount(num * 10 + 1, target, count + 1);   // 1을 수의 가장 오른쪽에 추가하는 연산
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        System.out.println(new Solution().solution(A, B));
    }
}