import java.io.*;

class Solution {

    String solution(String numStr) {
        String answer = null;   // 1~n까지 수를 차례대로 이어 붙일 때, n의 최소값

        int n = 1;
        int i = 0;

        whileLoop:
        while (true) {
            // 특정 숫자의 어느 자리가 지워진지 알 수 없기 때문에 각 자리마다 비교
            for (char c : String.valueOf(n).toCharArray()) {
                if (c == numStr.charAt(i)) {
                    i++;

                    if (numStr.length() <= i) {
                        answer = String.valueOf(n);
                        break whileLoop;
                    }
                }
            }
            n++;
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String numStr = br.readLine();

        System.out.println(new Solution().solution(numStr));
    }
}