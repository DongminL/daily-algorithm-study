import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        int count = num;

        for (int i = 0; i < num; i++) {
            String word = br.readLine();

            if (check(word) == false)
                count--;
        }

        System.out.println(count);

        br.close();
    }

    public static boolean check(String word) {  // false : 그룹 단어 X, true : 그룹 단어 O
        if (word.length() <= 2)
            return true;

        boolean[] alphabet = new boolean[26];   // false : 없음, true : 한번 이상 나옴
        int previous = 0;

        alphabet[word.charAt(previous) - 'a'] = true;

        for (int j = 1; j < word.length(); j++) {
            if (alphabet[word.charAt(j) - 'a'] == false)
                alphabet[word.charAt(j) - 'a'] = true;

            else if (alphabet[word.charAt(j) - 'a'] == true) {
                if (word.charAt(previous) != word.charAt(j))
                    return false;
            }
            previous++;
        }

        return true;
    }
}