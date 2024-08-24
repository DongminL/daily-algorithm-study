import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n;    // 컴퓨터의 개수
    int totalLength;    // 주어진 랜선의 총 길이
    PriorityQueue<Lan> list;    // 연결된 랜선의 목록
    int[] represent;    // 각 부분 그래프의 대표자

    /* 컴퓨터 그룹의 대표자 탐색 */
    private int find(int a) {
        if (represent[a] == a) {
            return a;
        }

        return find(represent[a]);
    }

    /* 두 컴퓨터 그룹 연결 (+ 사이클 여부 판단) */
    private boolean union(Lan lan) {
        int representA = find(lan.a);    // A의 대표자
        int representB = find(lan.b);    // B의 대표자

        if (representA != representB) {
            represent[representB] = representA;
            return true;    // 합병 시 사이클이 없음
        }

        return false;    // 합병 시 사이클이 발생함
    }

    /* 기부할 랜선의 길이 구하기 (MST 알고리즘) */
    private int donation() {
        int donationLen = totalLength;    // 기부할 랜선의 길이
        int lanCnt = 0;    // 연결된 랜선의 개수

        // MST 구성
        while (lanCnt != n-1) {
            if (!list.isEmpty()) {
                Lan cur = list.poll();    // 현재 가져온 랜선

                // 두 컴퓨터 그룹이 연결될 때
                if (union(cur)) {
                    donationLen -= cur.length;
                    lanCnt++;
                }

            } else {    // 모든 컴퓨터가 연결되지 않을 때
                return -1;
            }
        }

        return donationLen;
    }

    private void solution() throws IOException {
        n = Integer.parseInt(br.readLine());

        // 변수, 배열 및 리스트 초기화
        totalLength = 0;
        list = new PriorityQueue<>();
        represent = new int[n];

        // 랜선의 길이 입력받기
        IntStream.range(0, n).forEach(i -> {
            represent[i] = i;    // 각 컴퓨터의 대표자 초기값

            try {
                // 연결된 랜선의 길이
                char[] lens = br.readLine().toCharArray();
                IntStream.range(0, n).forEach(j -> {
                    int length = toNumber(lens[j]);    // 문자열 -> 수

                    // 랜선 추가
                    if (length > 0 && i != j) {
                        list.offer(new Lan(i, j, length));
                    }
                    totalLength += length;
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // 기부할 랜선의 길이 출력
        System.out.println(donation());
    }

    /* 문자열로 주어진 길이를 숫자로 변환 */
    private int toNumber(char c) {
        // 소문자일 때 (1 ~ 26)
        if (c >= 'a') {
            return c - 96;
        } else if (c >= 'A') {    // 대문자일 때 (27 ~ 52)
            return c - 38;
        }

        // 0일 때
        return 0;
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

/* 랜선의 정보에 대한 클래스 */
class Lan implements Comparable<Lan> {

    int a;    // 랜선에 연결된 컴퓨터 A
    int b;    // 랜선에 연결된 컴퓨터 B
    int length;    // 랜선의 길이

    public Lan(int a, int b, int length) {
        this.a = a;
        this.b = b;
        this.length = length;
    }

    @Override
    public int compareTo(Lan other) {
        return Integer.compare(this.length, other.length);    // 오름차순 정렬
    }
}