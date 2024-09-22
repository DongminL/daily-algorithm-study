import java.io.*;
import java.util.*;

class Solution {

    int answer; // S를 T로 바꿀 수 있으면 1을 없으면 0

    public int solution(String S, String T) {
        answer = 0;
        dfs(S, S.length(), T, T.length());

        return answer;
    }

    /* 두 가지의 연산을 통해 T를 S로 만들기 */
    private void dfs(String S, int length, String cur, int curLength) {
        // 문자열의 길이가 같아질 때까지만 만들기
        if (curLength == length) {
            if (S.equals(cur)) {
                answer = 1;
            }
            return;
        }

        // 순서를 알지 못함으로 각 경우를 따로 진행함으로써 확인
        // A를 추가하는 연산을 거꾸로
        if (cur.charAt(curLength - 1) == 'A') {
            dfs(S, length, cur.substring(0, curLength-1), curLength - 1);
        }
        // B를 추가하는 연산을 거꾸로
        if (cur.charAt(0) == 'B') {
            cur = new StringBuilder(cur.substring(1)).reverse().toString();
            dfs(S, length, cur, curLength - 1);
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();    // 문자열 S
        String T = br.readLine();    // 문자열 T

        System.out.println(new Solution().solution(S, T));;
    }
}