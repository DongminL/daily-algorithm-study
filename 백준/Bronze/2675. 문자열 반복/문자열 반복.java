import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCnt = Integer.parseInt(br.readLine());

        for ( int count = 0; count < testCnt; count++) {
            st = new StringTokenizer(br.readLine());

            int repeatCnt = Integer.parseInt(st.nextToken());
            String word = st.nextToken();

            for (int j = 0; j < word.length(); j++) {
                for (int i = 0; i < repeatCnt; i++) {
                    System.out.print(word.charAt(j));
                }
            }
            System.out.println();
        }

        br.close();
    }
}