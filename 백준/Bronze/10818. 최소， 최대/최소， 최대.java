import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        int value;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= n; i++) {
            value = Integer.parseInt(st.nextToken());

            max = max > value ? max : value;
            min = min < value ? min : value;
        }

        bw.write(min + " " + max);

        br.close();
        bw.flush();
        bw.close();
    }
}