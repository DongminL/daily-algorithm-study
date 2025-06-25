import java.io.*;

class Solution {

    String solution(String s) {
        // s를 구성하는 문자 중 절반의 0과 절반의 1을 제거하여 사전순으로 가장 빠른 새로운 문자열
        char[] answer = s.toCharArray();

        int[] halfCount = new int[2];
        for (char c : answer) {
            halfCount[c - '0']++;
        }
        halfCount[0] /= 2;
        halfCount[1] /= 2;

        // 앞에 있는 1부터 제거하기
        for (int i = 0; i < s.length(); i++) {
            if (halfCount[1] < 1) {
                break;
            }

            if (answer[i] == '1') {
                answer[i] = ' ';
                halfCount[1]--;
            }
        }

        // 뒤에 있는 0부터 제거하기
        for (int i = s.length() - 1; i > -1 ; i--) {
            if (halfCount[0] < 1) {
                break;
            }

            if (answer[i] == '0') {
                answer[i] = ' ';
                halfCount[0]--;
            }
        }

        // 공백 제거 후 반환
        return String.valueOf(answer)
            .replace(" ", "");
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        System.out.println(new Solution().solution(s));
    }
}