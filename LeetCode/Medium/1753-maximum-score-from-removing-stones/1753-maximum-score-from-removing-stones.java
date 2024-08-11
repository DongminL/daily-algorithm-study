class Solution {
    public int maximumScore(int a, int b, int c) {
        int score = 0;  // 가질 수 있는 최대 점수

        // 돌 더미를 내림차순으로 우선순위 큐에 넣어 정렬
        PriorityQueue<Integer> sortedPiles = new PriorityQueue<>(Collections.reverseOrder());
        sortedPiles.offer(a);
        sortedPiles.offer(b);
        sortedPiles.offer(c);

        // 비어있지 않은 돌 더미의 개수가 2개보다 작을 때 멈춤
        while (sortedPiles.size() > 1) {
            int pile1 = sortedPiles.poll() - 1;    // 가장 많은 개수의 돌 더미에서 -1개
            int pile2 = sortedPiles.poll() - 1;    // 두 번째로 많은 개수의 돌 더미에서 -1개

            score++;

            // 1개씩 감소 시킨 후, 다시 넣음으로써 정렬 (0개면 비어있는 돌 더미이므로 다시 넣지 않음) 
            if (pile1 > 0)
                sortedPiles.offer(pile1);
            if (pile2 > 0)
                sortedPiles.offer(pile2);
        }

        return score;
    }
}