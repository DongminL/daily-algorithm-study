import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n;
        n = s.nextInt();
        System.out.print(factorial(n));
    }
    static int factorial(int n){
    if (n == 0 || n == 1) return 1;
    return n * factorial(n-1);
}
}