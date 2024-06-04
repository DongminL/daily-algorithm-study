import java.io.*;
class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int a;
        int[] rest = new int[42];
        int count = 0;

        for(int i = 0; i < 42; i++)
            rest[i] = i;

        for(int i = 0; i < 10; i++){
            a = Integer.parseInt(br.readLine());

            rest[a % 42] = -1;
        }

        br.close();

        for(int i = 0; i < 42; i++){
            if(rest[i] == -1)
                count++;
        }

        bw.write(Integer.toString(count));

        bw.flush();
    }
}