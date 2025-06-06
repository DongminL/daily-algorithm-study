import java.io.*;

class Solution {

    String solution(int n) {
        // index : 돌의 개수
        // true : 상근이 승리, false : 창영이 승리 (상근이 패배)
        boolean[] winners = new boolean[n+1];
        winners[1] = true;

        if (n >= 2) {
            winners[2] = false;
        }
        if (n >= 3) {
            winners[3] = true;
        }

        for (int i = 4; i <= n; i++) {
            // 상근이가 먼저 가져간 후, 남은 개수에서 창영이가 먼저 가져가기 때문에
            // 해당 개수의 기록에서 반대 결과로 결정됨
            winners[i] = !winners[i - 1] || !winners[i - 3];
        }

        return winners[n] ? "SK" : "CY";
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());    // 돌의 개수

        System.out.println(new Solution().solution(n));
    }
}