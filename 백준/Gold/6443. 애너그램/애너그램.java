import java.io.*;
import java.util.*;

class Solution {

    StringBuilder answer; // 애너그램

    public String solution(int n, String[] words) {
        answer = new StringBuilder();   // 애너그램 출력 결과

        // 애너그램 프로그램의 결과 가져오기
        Arrays.stream(words)
                .forEach(word -> {
                    // 해당 단어에서 각 알파벳의 개수
                    int[] countAlphabet = new int[26];
                    for (char alphabet : word.toCharArray()) {
                        countAlphabet[alphabet - 'a']++;
                    }

                    anagram("",0, word.length(), countAlphabet);
                });

        return answer.toString();
    }

    /* 입력받은 단어 전부 찾기 (DFS) */
    private void anagram(String cur, int depth, int strLength, int[] countAlphabet) {
        // 하나의 새로운 단어를 만들면 추가
        if (depth == strLength) {
            answer.append(cur).append("\n");
            return;
        }

        // 남은 알파벳을 추가하며 단어 만들기
        for (int i = 0; i < countAlphabet.length; i++) {
            if (countAlphabet[i] > 0) {
                countAlphabet[i]--;
                anagram(cur + (char)(i + 'a'), depth + 1, strLength, countAlphabet);
                countAlphabet[i]++;
            }
        }

    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());    // 단어의 개수
        // 입력된 영단어들
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        System.out.println(new Solution().solution(N, words));;
    }
}