import java.io.*;
import java.util.*;

class Solution {

    int[] solution(int n, int m, String[] keywords, String[] writing) {
        // i번째 글을 쓰고 난 후, 메모장에 남아 있는 키워드의 개수
        int[] answer = new int[m];

        Set<String> memo = new HashSet<>(List.of(keywords));

        for (int i = 0; i < m; i++) {
            String[] words = writing[i].split(",");

            for (String word : words) {
                memo.remove(word);
            }

            answer[i] = memo.size();
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputInfo = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        String[] keywords = new String[inputInfo[0]];
        for (int i = 0; i < inputInfo[0]; i++) {
            keywords[i] = br.readLine();
        }

        String[] writing = new String[inputInfo[1]];
        for (int i = 0; i < inputInfo[1]; i++) {
            writing[i] = br.readLine();
        }

        Arrays.stream(new Solution().solution(inputInfo[0], inputInfo[1], keywords, writing))
            .forEach(System.out::println);
    }
}