import java.io.*;
import java.util.*;

class Solution {

    private StringBuilder answer = new StringBuilder(); // 쿼드 트리
    private int[][] videoBits;  // 영상 비트

    public String solution(int n, int[][] bits) {
        videoBits = bits;
        quadTree(0, 0, n);

        return answer.toString();
    }

    private void quadTree(int x, int y, int size) {
        // 해당 영역 압축하기
        if (isCompressible(x, y, size)) {
            answer.append(videoBits[y][x]);
            return;
        }

        int dividedSize = size / 2;   // 분할된 크기

        answer.append("(");

        quadTree(x, y, dividedSize); // 왼쪽 위
        quadTree(x + dividedSize, y, dividedSize); // 오른쪽 위
        quadTree(x, y + dividedSize, dividedSize); // 왼쪽 아래
        quadTree(x + dividedSize, y + dividedSize, dividedSize); // 오른쪽 아래

        answer.append(")");
    }

    // 압축 가능한지 여부
    private boolean isCompressible(int x, int y, int size) {
        int bit = videoBits[y][x];  // (x,y) 좌표의 비트

        // 해당 영역 검사
        for (int i = y; i < y + size; i++) {
            for(int j = x; j < x + size; j++) {
                if (bit != videoBits[i][j]) {   // 인덱스 범위 고려 X (들어오는 사이즈가 2의 제곱만큼 줄어들기 때문)
                    return false;
                }
            }
        }
        return true;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());    // 영상의 크기
        int[][] bits = new int[N][N];   // 입력된 비트
        for (int i = 0; i < N; i++) {
            bits[i] = Arrays.stream(br.readLine().split(""))
                .mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(new Solution().solution(N,  bits));
    }
}