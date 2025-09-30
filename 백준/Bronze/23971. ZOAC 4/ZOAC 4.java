import java.io.*;
import java.util.*;

class Solution {

    int solution(int h, int w, int n, int m) {
        // 강의실이 수용할 수 있는 최대 인원 수
        int answer = 0;

        for (int row = 0; row < h; row += n + 1) {
            for (int col = 0; col < w; col += m + 1) {
                answer ++;
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

        System.out.println(
            new Solution().solution(inputInfo[0], inputInfo[1], inputInfo[2], inputInfo[3])
        );
    }
}