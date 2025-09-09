import java.io.*;
import java.util.*;

class Solution {

    private final int n;

    // ASCII 순서에 따라 결과가 0이 되는 모든 수식
    private List<String> answer = new ArrayList<>();

    // 수식에 사용되는 기호들
    private static final char[] SYMBOLS = { '+', '-', ' ' };

    public Solution(int n) {
        this.n = n;
    }

    List<String> solution() {
        makeZeroFormula(1, "1");
        
        // ASCII 순서로 정렬
        Collections.sort(answer);

        return answer;
    }

    // 0이 되는 공식 만들기
    private void makeZeroFormula(int curNum, String formula) {
        if (curNum == n) {
            if (isZero(formula)) {
                answer.add(formula);
            }
            return;
        }

        for (char symbol : SYMBOLS) {
            makeZeroFormula(curNum + 1, formula + symbol + (curNum + 1));
        }
    }

    // 해당 수식의 결과가 0인지 확인
    private boolean isZero(String formula) {
        // 공백 제거
        formula = formula.replace(" ", "");

        StringTokenizer st = new StringTokenizer(formula, "+|-", true);
        int sum = Integer.parseInt(st.nextToken());

        // 수식 결과 구하기
        while (st.hasMoreTokens()) {
            String current = st.nextToken();

            if (current.equals("+")) {
                sum += Integer.parseInt(st.nextToken());

            } else {
                sum -= Integer.parseInt(st.nextToken());
            }
        }

        // 수식 결과가 0인지 boolean 값 반환
        return sum == 0;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] testCases = new int[n];
        for (int i = 0; i < n; i++) {
            testCases[i] = Integer.parseInt(br.readLine());
        }

        StringBuilder result = new StringBuilder();
        for (int testCase : testCases) {
            List<String> answer = new Solution(testCase).solution();

            answer.forEach(e -> result.append(e).append("\n"));
            result.append("\n");
        }
        System.out.println(result);
    }
}