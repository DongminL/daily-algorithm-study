import java.io.*;
import java.util.*;

public class Main {
    
    static void cardSort(int n, PriorityQueue<Integer> cards) {
        int sum = 0;
        while (cards.size() > 1) {
            int card1 = cards.poll();
            int card2 = cards.poll();
            
            sum += (card1 + card2);
            
            cards.offer(card1 + card2);
        }
        
        System.out.println(sum);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> cards = new PriorityQueue<>();
        
        for (int i = 0; i < n; i++) {
            cards.offer(Integer.parseInt(br.readLine()));
        }
        
        cardSort(n, cards);
    }
}