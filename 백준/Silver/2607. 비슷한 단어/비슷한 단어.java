import java.io.*;
import java.util.*;

class Solution {

    int solution(String first, int n, String[] words) {
        int answer = 0;   // 첫 번째 단어와 비슷한 단어의 개수

        Map<Character, Integer> firstWordCount = countSpelling(first);

        for (String word : words) {
            int lengthDifference = first.length() - word.length();

            // 한 글자까지만 추가, 삭제, 변경 가능함
            if (-1 <= lengthDifference && lengthDifference <= 1) {
                int different = countDifferent(firstWordCount, countSpelling(word));

                // 동일한 구성이거나, 하나의 문자만 추가하거나 빼는 경우
                if (different <= 1) {
                    answer++;
                }

                // 하나의 문자를 다른 문자로 변경하는 경우
                if (different == 2 && lengthDifference == 0) {
                    answer++;
                }
            }
        }

        return answer;
    }

    // 단어의 스펠링 개수 세기
    private Map<Character, Integer> countSpelling(String word) {
        Map<Character, Integer> count = new HashMap<>();
        for (char c : word.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        return Collections.unmodifiableMap(count);
    }

    // 두 단어의 문자 개수의 차이
    private int countDifferent(
        Map<Character, Integer> base,
        Map<Character, Integer> target
    ) {
        // 두 단어에 나오는 모든 문자
        Set<Character> spelling = new HashSet<>();
        spelling.addAll(base.keySet());
        spelling.addAll(target.keySet());

        // 문자별 개수 차이 누적하기
        int differentCount = 0;
        for (char c : spelling) {
            int baseCount = base.getOrDefault(c, 0);
            int targetCount = target.getOrDefault(c, 0);

            differentCount += Math.abs(baseCount - targetCount);
        }

        return differentCount;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String first = br.readLine();

        String[] words = new String[n - 1];
        for (int i = 0; i < n - 1; i++) {
            words[i] = br.readLine();
        }

        System.out.println(new Solution().solution(first, n - 1, words));
    }
}