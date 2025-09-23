import java.io.*;

class Solution {

    String[] solution(int n, String[] words) {
        // 0: S가 입력되는 순서대로 제일 앞쪽에 있는 단어
        // 1: T가 입력되는 순서대로 제일 앞쪽에 있는 단어
        String[] answer = new String[2];

        int maxPrefixLength = -1;

        for (int i = 0; i < n; i++) {
            String word1 = words[i];

            for (int j = i + 1; j < n; j++) {
                String word2 = words[j];

                int prefixLength = getPrefixLength(word1, word2);

                // 접두사의 최대 길이가 갱신되는 경우, 입력된 순서에서 제일 앞쪽 단어로 교체
                if (maxPrefixLength < prefixLength) {
                    maxPrefixLength = prefixLength;

                    answer[0] = word1;
                    answer[1] = word2;
                }
            }
        }

        return answer;
    }


    // 두 문자열의 같은 접두사의 길이
    private int getPrefixLength(String s1, String s2) {
        int length = Math.min(s1.length(), s2.length());
        int prefixLength = 0;

        for (int i = 0; i < length; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                break;
            }
            prefixLength++;
        }

        return prefixLength;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        String[] result = new Solution().solution(n, words);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}