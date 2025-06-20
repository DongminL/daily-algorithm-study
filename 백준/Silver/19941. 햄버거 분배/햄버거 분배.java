import java.io.*;
import java.util.*;

class Solution {

    int solution(int n, int k, char[] table) {
        int answer = 0;   // 햄버거를 먹을 수 있는 최대 인원

        char hamburger = 'H';
        char person = 'P';
        char ate = 'E';

        for (int i = 0; i < n; i++) {
            if (table[i] == person) {

                // 특정 사람의 좌우로부터 k 거리의 햄버거 찾기
                for (int j = i - k; j <= (i + k); j++) {
                    if (0 <= j && j < n && table[j] == hamburger) {
                        table[j] = ate;
                        answer++;
                        break;
                    }
                }
            }
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
        char[] table = br.readLine().toCharArray();

        System.out.println(new Solution().solution(inputInfo[0], inputInfo[1], table));
    }
}