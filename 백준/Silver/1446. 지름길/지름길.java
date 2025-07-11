import java.io.*;
import java.util.*;

class Solution {

    int solution(int n, int d, int[][] shortcuts) {
        // i 위치까지 최소 거리
        int[] distance = new int[d + 1];
        for (int i = 0; i <= d; i++) {
            distance[i] = i;
        }

        // 시작 위치가 같은 지름길끼리 그룹화
        Map<Integer, List<Point>> mapped = new HashMap<>();
        for (int[] shortcut : shortcuts) {
            if (!mapped.containsKey(shortcut[0])) {
                mapped.put(shortcut[0], new ArrayList<>());
            }

            List<Point> list = mapped.get(shortcut[0]);
            list.add(new Point(shortcut[1], shortcut[2]));
        }

        // 지나온 거리에 대해서 오름차순 정렬 (같을 경우 더 멀리 있는 지점순으로)
        PriorityQueue<Point> heap = new PriorityQueue<>((p1, p2) -> {
            if (p1.dist == p2.dist) {
                return Integer.compare(p2.dest, p1.dest);
            }
            return Integer.compare(p1.dist, p2.dist);
        });

        // 시작 지점
        heap.offer(new Point(0, 0));

        while (!heap.isEmpty()) {
            Point current = heap.poll();

            // 현재 온 거리가 최소로 갈 수 있는 거리보다 긴 경우
            if (distance[current.dest] < current.dist) {
                continue;
            }

            // 지름길로 가는 경우
            if (mapped.containsKey(current.dest)) {
                for (Point shortcut : mapped.get(current.dest)) {
                    // 고속도로 길이를 넘는 경우 지름길 가지 않기 (역주행을 못하기 때문)
                    if (d < shortcut.dest) {
                        continue;
                    }

                    // 지름길을 이용할 때 거리가 더 길어지는 경우
                    if (distance[shortcut.dest] < distance[current.dest] + shortcut.dist) {
                        continue;
                    }

                    distance[shortcut.dest] = distance[current.dest] + shortcut.dist;
                    heap.offer(new Point(shortcut.dest, distance[shortcut.dest]));
                }
            }

            // 운전 거리를 최소한으로 유지하며 고속도로로 달리는 경우
            if (current.dest + 1 <= d && current.dist + 1 <= distance[current.dest + 1]) {
                distance[current.dest + 1] = current.dist + 1;
                heap.offer(new Point(current.dest + 1, distance[current.dest + 1]));
            }
        }

        return distance[d];
    }

    private static class Point {

        int dest;   // 현재 목적지
        int dist;   // 그동안 지나온 거리

        public Point(int dest, int dist) {
            this.dest = dest;
            this.dist = dist;
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputInfo = toIntArray(br.readLine());

        int[][] shortcuts = new int[inputInfo[0]][3];
        for (int i = 0; i < inputInfo[0]; i++) {
            shortcuts[i] = toIntArray(br.readLine());
        }

        System.out.println(new Solution().solution(inputInfo[0], inputInfo[1], shortcuts));
    }

    private static int[] toIntArray(String inputStr) {
        return Arrays.stream(inputStr.split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
    }
}