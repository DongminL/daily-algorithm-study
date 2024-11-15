import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution {

    public int solution(int n, String[] words) {
        // N개의 단어가 주어졌을 때, 그 수의 합의 최대값
        int answer = 0;

        // 각 알파벳의 자릿수의 총합
        int[] alphabetDigitSum = new int[26];

        // 단어에서 각 자리수를 해당 알파벳 자릿수 총합에 더한다.
        // ex) ABAB -> A : 1000 + 10, B : 100 + 1
        for (String word : words) {
            int wordLength = word.length();

            for (int i = 0; i < wordLength; i++) {
                int alphabet = word.charAt(i) - 'A';    // 알파벳의 순번

                // 해당 알파벳 자릿수를 누적하여 더한다.
                alphabetDigitSum[alphabet] += (int) Math.pow(10, wordLength - i - 1);
            }
        }

        // 오름차순 정렬
        Arrays.sort(alphabetDigitSum);

        int alphabetValue = 9;    // 알파벳에 매핑되는 수

        // 높은 자리수에 많이 나온 알파벳부터 9부터 1까지 곱해주고 누적하여 더함
        for (int i = alphabetDigitSum.length - 1; i > -1; i--) {
            if (alphabetDigitSum[i] == 0) {
                break;
            }

            // 알파벳이 나온 자릿수의 합에 매핑되는 수를 곱해준다.
            answer += (alphabetDigitSum[i] * alphabetValue);

            alphabetValue--;
        }

        return answer;
    }
}

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());    // 문자열의 개수
        String[] words = new String[N]; // 입력된 문자열들

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        System.out.println(new Solution().solution(N, words));
    }
}