import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num;
        int count;
        int[] score;
        String st;

        num = Integer.parseInt(br.readLine());

        score = new int[num];

        for (int i = 0; i < num; i++) {
            count = 0;
            st = br.readLine();

            for (int j = 0; j < st.length(); j++) {
                if (st.substring(j, j + 1).equals("O")) {
                    count++;
                    score[i] += count;
                } else if (st.substring(j, j + 1).equals("X"))
                    count = 0;
            }
        }

        for (int i = 0; i < num; i++) {
            bw.write(Integer.toString(score[i]));
            bw.newLine();
        }

        br.close();
        bw.flush();
    }
}
