import java.io.*;

class Solution {

    public int solution(String expression) {
        int answer = 0; // 적절한 괄호를 사용한 식의 최소값

        int plusSum = 0;    // 마이너스가 나오기 이전의 합
        int minusSum = 0;   // 마이너스가 나온 뒤의 합

        boolean hasMinusBefore = false; // 이전에 마이너스가 나왔는지 확인
        StringBuilder numStr = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (c == '+') {
                if (hasMinusBefore) {
                    minusSum += Integer.parseInt(numStr.toString());
                } else {
                    plusSum += Integer.parseInt(numStr.toString());
                }

                numStr = new StringBuilder();
                continue;
            }

            // 마이너스가 나오면 뒤에 수들을 전부 더하게 함
            if (c == '-') {
                if (hasMinusBefore) {
                    minusSum += Integer.parseInt(numStr.toString());
                } else {
                    plusSum += Integer.parseInt(numStr.toString());
                }

                hasMinusBefore = true;

                numStr = new StringBuilder();
                continue;
            }

            // 문자열 형태의 숫자를 만듦
            numStr.append(c);
        }

        // 마지막 수 더해주기
        if (hasMinusBefore) {
            minusSum += Integer.parseInt(numStr.toString());
        } else {
            plusSum += Integer.parseInt(numStr.toString());
        }

        answer = plusSum - minusSum;
        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();

        System.out.println(new Solution().solution(expression));
    }
}