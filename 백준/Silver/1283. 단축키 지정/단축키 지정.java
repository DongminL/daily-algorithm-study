import java.io.*;
import java.util.*;

class Solution {

    String[] solution(int n, String[] words) {
        String[] answer = new String[n];
        Set<Character> keys = new HashSet<>();

        for (int i = 0; i < n; i++) {
            String line = words[i];
            String[] parts = line.split(" ");
            boolean assigned = false;

            // 각 단어의 첫 글자에 대해서 단축키 확인
            for (int j = 0; j < parts.length; j++) {
                char c = Character.toLowerCase(parts[j].charAt(0));
                if (!keys.contains(c)) {
                    keys.add(c);
                    parts[j] = "[" + parts[j].charAt(0) + "]" + parts[j].substring(1);
                    assigned = true;
                    break;
                }
            }

            // 전체 문장에서 아직 사용되지 않은 문자 확인
            if (!assigned) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < line.length(); j++) {
                    char c = line.charAt(j);
                    if (c != ' ' && !keys.contains(Character.toLowerCase(c))) {
                        keys.add(Character.toLowerCase(c));
                        sb.append("[")
                            .append(c)
                            .append("]");
                        sb.append(line.substring(j + 1));
                        break;
                    } else {
                        sb.append(c);
                    }
                }
                answer[i] = sb.toString();
            } else {
                answer[i] = String.join(" ", parts);
            }
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        Arrays.stream(new Solution().solution(n, words))
            .forEach(System.out::println);
    }
}