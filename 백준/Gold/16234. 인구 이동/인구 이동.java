import java.io.*;
import java.util.*;

class Solution {

    private final int n;
    private final int l;
    private final int r;
    private int[][] countries;

    public Solution(int n, int l, int r, int[][] countries) {
        this.n = n;
        this.l = l;
        this.r = r;
        this.countries = countries;
    }

    int solution () {
        // 인구 이동을 하는 기간 (일)
        int answer = 0;

        // 인구 이동 시작
        while (true) {
            boolean isMove = false;
            boolean[][] visited = new boolean[n][n];
            
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    if (!visited[y][x]) {
                        List<Country> united = unite(x, y, visited);

                        // 국경을 개방한 경우 (연합한 경우)
                        if (united.size() > 1) {
                            isMove = true;
                            updateMovedState(united);
                        }
                    }
                }
            }
            
            // 더이상 인구 이동을 못하는 경우
            if (!isMove) {
                break;
            }

            answer++;
        }

        return answer;
    }

    // 인구 이동이 된 상태로 인구수 수정
    private void updateMovedState(List<Country> united) {
        // 연합국 전체 인구수
        int total = 0;
        for (Country country : united) {
            total += countries[country.y][country.x];
        }

        // 인구 분배
        int movedPopulation = total / united.size();
        for (Country country : united) {
            countries[country.y][country.x] = movedPopulation;
        }
    }

    // 적정 인구수 차이면 구경선을 개방하여 연합하기
    private List<Country> unite(int x, int y, boolean[][] visited) {
        // 연합 국가들
        List<Country> united = new ArrayList<>();

        // 북, 동, 남, 서 방향
        int[] dX = { 0, 1, 0, -1 };
        int[] dY = { -1, 0, 1, 0 };

        Queue<Country> queue = new ArrayDeque<>();

        // 이동 시작 국가 설정
        queue.offer(new Country(x, y));
        visited[y][x] = true;

        // 연합국 모으기
        while (!queue.isEmpty()) {
            Country current = queue.poll();

            united.add(current);

            // 4방위 탐색
            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dX[i];
                int nextY = current.y + dY[i];

                if (canMove(current.x, current.y, nextX, nextY) && !visited[nextY][nextX]) {
                    queue.offer(new Country(nextX, nextY));
                    visited[nextY][nextX] = true;
                }
            }
        }

        return united;
    }

    // 인구 이동이 가능한지 여부
    private boolean canMove(int currentX, int currentY, int nextX, int nextY) {
        return isLand(nextX, nextY) && canOpen(currentX, currentY, nextX, nextY);
    }

    // 땅인지 여부
    private boolean isLand(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    // 국경선을 개방할 수 있는 여부
    private boolean canOpen(int x1, int y1, int x2, int y2) {
        int diff = Math.abs(countries[y1][x1] - countries[y2][x2]); // 인구수 차이
        return l <= diff && diff <= r;
    }

    private static class Country {

        int x;
        int y;

        public Country(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputInfo = toIntArray(br.readLine().split(" "));

        int[][] countries = new int[inputInfo[0]][inputInfo[0]];
        for (int i = 0; i < countries.length; i++) {
            countries[i] = toIntArray(br.readLine().split(" "));
        }

        System.out.println(
            new Solution(inputInfo[0], inputInfo[1], inputInfo[2], countries).solution()
        );
    }

    private static int[] toIntArray(String[] strArray) {
        return Arrays.stream(strArray)
            .mapToInt(Integer::parseInt)
            .toArray();
    }
}