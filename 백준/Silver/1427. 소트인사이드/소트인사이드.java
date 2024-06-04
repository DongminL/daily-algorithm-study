import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String num = br.readLine();
        char[] reverseNum = new char[num.length()];

        for (int i = 0; i < reverseNum.length; i++) {
            reverseNum[i] = num.charAt(i);
        }

        Arrays.sort(reverseNum);

        for (int i = reverseNum.length - 1; i >= 0; i--) {
            bw.write(reverseNum[i]);
        }

        bw.flush();
        bw.close();
        br.close();
    }
}