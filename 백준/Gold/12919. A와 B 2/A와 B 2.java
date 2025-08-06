import java.io.*;

class Solution {

    // s를 t로 바꿀 수 있는 여부
    private boolean answer = false;

    int solution(String s, String t) {
        makeTToS(s, t);

        return answer ? 1 : 0;
    }

    // t 문자열에서 s로 만들기
    private void makeTToS(String s, String t) {
        if (s.length() == t.length()) {
            if (s.equals(t)) {
                answer = true;
            }
            return;
        }

        // A가 추가되었을 때 되돌리기
        if (t.charAt(t.length() - 1) == 'A') {
            makeTToS(s, t.substring(0, t.length() - 1));
        }

        // B가 추가되었을 때 되돌리기
        if (t.charAt(0) == 'B') {
            StringBuilder next = new StringBuilder(t);
            makeTToS(s, next.delete(0, 1).reverse().toString());
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String t = br.readLine();

        System.out.println(new Solution().solution(s, t));
    }
}