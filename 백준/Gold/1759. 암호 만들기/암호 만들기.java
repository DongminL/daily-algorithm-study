import java.io.*;
import java.util.*;

class Solution {

    List<String> passwords;  // 가능한 암호의 몽
    boolean[] usedChar;  // 사용한 글자 유무

    public String solution(int length, String[] chars) {
        StringBuilder answer = new StringBuilder(); // 가능성 있는 암호들
        passwords = new ArrayList<>();
        usedChar = new boolean[chars.length];

        // 암호에 사용되는 문자 오름차순 정렬
        Arrays.sort(chars);

        // 가능성 있는 암호 만들기
        makePassword(length, chars, 0, "");

        // 가능성 있는 암호들 오름차순 정렬
        Collections.sort(passwords);

        // Set<String> -> String 각 암호마다 줄바꿈
        for (String s : passwords) {
            answer.append(s).append("\n");
        }

        return answer.toString();
    }

    /* length 길이의 가능성 있는 암호 만들기 */
    private void makePassword(int length, String[] chars, int index, String password) {
        // 유효한 암호인지 확인
        if (length == password.length()) {
            if (validPassword(length, password)) {
                passwords.add(password);
            }
            return;
        }

        // 다른 가능성 있는 암호 탐색
        for (; index < chars.length; index++) {
            // 글자 중복 방지
            if (!usedChar[index]) {
                usedChar[index] = true;
                makePassword(length, chars, index + 1, password + chars[index]);
            }

            // 이제 password에 없는 글자이므로 usedChar[index] false로 설정
            usedChar[index] = false;
        }
    }

    /* 유효한 암호인지 검사 */
    private boolean validPassword(int length, String password) {
        int passwordLength = password.length();  // 암호의 길이
        int countVowel = 0;  // 자음의 개수

        // 자음와 모음의 개수 파악
        for (int i = 0; i < passwordLength; i++) {
            if (checkVowel(password.charAt(i))) {
                countVowel++;
            }
        }

        // 모음은 최소 하나 이상, 자음은 두 개 이상이어야 한다. (암호의 최소 길이는 3)
        return countVowel >= 1 && passwordLength - countVowel >= 2;
    }

    /* 모음인지 검사 */
    private boolean checkVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] arr = toIntArray();
        int L = arr[0];  // 암호는 서로 다른 L개의 알파벳 소문자들로 구성
        int C = arr[1];  // C개의 문자들

        // 암호에 사용되는 글자들
        String[] chars = br.readLine().split(" ");

        System.out.println(new Solution().solution(L, chars));
    }

    /* 입력값 (문자열) -> int[] */
    private static int[] toIntArray() throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}