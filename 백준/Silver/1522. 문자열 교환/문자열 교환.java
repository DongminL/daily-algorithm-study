import java.io.*;

class Solution {

    int solution(String str) {
        // 'a'를 모두 연속으로 만들기 위한 교환 횟수의 최소값
        int answer = 0;

        // 문자열에서 'a'의 개수
        int totalA = (int) str.chars().filter(c -> c == 'a').count();

        // 'a'의 개수 범위에서 'b'의 개수
        // 'a'를 모두 연속으로 하기 위해서는 해당 범위에 있는 'b'의 개수만큼 'a'와 교환하면 됨
        int bCount = 0;
        for (int i = 0; i < totalA; i++) {
            if (str.charAt(i) == 'b') {
                bCount++;
            }
        }
        answer = bCount;

        // 위의 작업을 Index: 1부터 범위를 1씩 움직이며 반복
        for (int start = 1; start < str.length(); start++) {
            int end = (start + totalA - 1) % str.length();

            // 범위를 1씩 이동할 때마다 `b`의 개수 갱신
            if (str.charAt(start - 1) == 'b') {
                bCount--;
            }
            if (str.charAt(end) == 'b') {
                bCount++;
            }

            answer = Math.min(answer, bCount);
        }

        return answer;
    }
}

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        System.out.println(new Solution().solution(str));
    }
}