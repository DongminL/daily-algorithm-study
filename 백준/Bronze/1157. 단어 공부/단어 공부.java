import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String vocalbulary = br.readLine();
        int[] alphabet = new int[26];
        int max = 0;
        char result = ' ';

        for (int i = 0; i < vocalbulary.length(); i++) {
            if (vocalbulary.charAt(i) < 91)
                alphabet[vocalbulary.charAt(i)-65]++;
            else if (vocalbulary.charAt(i) < 123)
                alphabet[vocalbulary.charAt(i)-97]++;
        }

        for (int i = 0; i < 26; i++) {
            if (max < alphabet[i]) {
                max = alphabet[i];
                result = (char) (i + 65);
            }

            else if (max == alphabet[i]) {
                result = '?';
            }
        }

        System.out.println(result);

        br.close();
    }
}