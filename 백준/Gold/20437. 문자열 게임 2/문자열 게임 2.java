import java.io.*;
import java.util.*;

class Solution {

    int[] solution(String word, int k) {
        // 문자열 게임의 3번과 4번에서 구한 연속 문자열의 길이
        // 0: 어떤 문자를 정확히 K개를 포함하는 가장 짧은 연속 문자열의 길이
        // 1: 어떤 문자를 정확히 K개를 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이
        int[] answer = { Integer.MAX_VALUE, Integer.MIN_VALUE };

        List<Integer>[] alphabetPos = getAlphabetPos(word);

        for (List<Integer> posList : alphabetPos) {
            // 특정 알파벳의 개수가 k개 미만이면 패스 (정확히 k개가 포함된 문자열의 길이를 구하기 때문)
            if (posList.size() < k) {
                continue;
            }

            // 특정 알파벳이 무조건 k개인 문자열의 길이를 구함
            for (int i = 0; i + k - 1 < posList.size(); i++) {
                int length = posList.get(i + k - 1) - posList.get(i) + 1;

                answer[0] = Math.min(answer[0], length);
                answer[1] = Math.max(answer[1], length);
            }
        }

        return answer;
    }

    // 단어에서 알파벳별로 나온 위치(인덱스) 기록
    private List<Integer>[] getAlphabetPos(String word) {
        List<Integer>[] alphabetPos = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            alphabetPos[i] = new ArrayList<>();
        }

        for (int i = 0; i < word.length(); i++) {
            alphabetPos[word.charAt(i) - 'a'].add(i);
        }

        return alphabetPos;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        String[] words = new String[T];
        int[] ks = new int[T];
        for (int i = 0; i < T; i++) {
            words[i] = br.readLine();
            ks[i] = Integer.parseInt(br.readLine());
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int[] testCase = new Solution().solution(words[i], ks[i]);

            if (testCase[0] == Integer.MAX_VALUE || testCase[1] == Integer.MIN_VALUE) {
                result.append(-1).append("\n");
                continue;
            }

            for (int length : testCase) {
                result.append(length).append(" ");
            }
            result.append("\n");
        }
        System.out.println(result);
    }
}