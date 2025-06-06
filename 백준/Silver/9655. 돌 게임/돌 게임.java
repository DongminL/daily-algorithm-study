import java.io.*;

class Solution {

    String solution(int n) {
        if (n % 2 == 1) {
            return "SK";
        } else {
            return "CY";
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());    // 돌의 개수

        System.out.println(new Solution().solution(n));
    }
}