import java.io.*;
import java.util.*;

class Solution {

    public int[] solution(int d, int k) {
        // {첫줄에 첫 날에 준 떡의 개수 A, 둘째 날에 준 떡의 개수 B}
        int[] answer = new int[2];

        if (d == 3) {
            answer[0] = k / 2;
            answer[1] = k - (k / 2);
            return answer;
        }

        // f3 = a * fn-2 + b * fn-1
        // fn = a * fn-2 + (a+b) * fn-1 (n >= 4)
        int a = 1;
        int b = 1;
        for (int i = 4; i <= d; i++) {
            int temp = b;
            b += a;
            a = temp;
        }

        // fn-2의 값이 변할 때 fn-1의 값을 구함
        for (int i = 1; i <= (k - a) / b; i++) {
            if ((k - a * i) % b == 0) {
                answer[0] = i;
                answer[1] = (k - a * i) / b;
                break;
            }
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 0: 할머니가 넘어온 날, 1: 그 날 호랑이에게 준 떡의 개수
        int[] inputInfo = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt).toArray();

        int[] answer = new Solution().solution(inputInfo[0], inputInfo[1]);

        StringBuilder output = new StringBuilder();
        Arrays.stream(answer).forEach(s -> output.append(s).append("\n"));
        System.out.println(output);
    }
}