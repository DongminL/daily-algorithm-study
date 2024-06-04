import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Coordinate[] coordinates = new Coordinate[n];
        String toString;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            coordinates[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(coordinates);

        for (int i = 0; i < n; i++) {
            bw.write(coordinates[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

class Coordinate implements Comparable<Coordinate>{
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return x + " " + y;
    }

    @Override
    public int compareTo(Coordinate coordinate) {
        if (coordinate.x == x)
            return y - coordinate.y;
        else
            return x - coordinate.x;
    }
}