import java.io.*;
import java.util.*;

class Solution {

    Set<String> solution(int n, int m, String[] words) {
        Map<String, Integer> count = new HashMap<>();
        for (String word : words) {
            if (m <= word.length()) {
                count.put(word, count.getOrDefault(word, 0) + 1);
            }
        }

        Set<String> sorted = new TreeSet<>((w1, w2) -> {
            if (Objects.equals(count.get(w1), count.get(w2))) {
                if (w1.length() == w2.length()) {
                    return String.CASE_INSENSITIVE_ORDER.compare(w1, w2);
                }
               return Integer.compare(w2.length(), w1.length());
            }
            return Integer.compare(count.get(w2), count.get(w1));
        });
        sorted.addAll(count.keySet());

        return sorted;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] inputInfo = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        int n = inputInfo[0];
        int m = inputInfo[1];
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        for (String word : new Solution().solution(n, m, words)) {
            bw.write(word + "\n");
        }
        bw.flush();
        
        bw.close();
        br.close();
    }
}