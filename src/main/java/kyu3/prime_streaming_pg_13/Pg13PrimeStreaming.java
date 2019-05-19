package kyu3.prime_streaming_pg_13;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Pg13PrimeStreaming {

    private static final int LIMIT = 15 * 1000 * 1000 + 100000 * 5;
        private static final int MAX_NUMBER = 20000000;

    public static IntStream stream() {
        return IntStream.range(2, LIMIT)
                .parallel()
                .filter(Pg13PrimeStreaming::isPrime);
    }

    private static boolean isPrime(int num){
        if ( num > 2 && num%2 == 0 ) {
            return false;
        }
        int top = (int)Math.sqrt(num) + 1;
        for(int i = 3; i < top; i+=2){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * Best performance that before method.
     * @return
     */
    public static IntStream stream2() {
        Pg13PrimeStreaming pg13PrimeStreaming = new Pg13PrimeStreaming();
        List<Integer> integerList = pg13PrimeStreaming.sieve();
        return integerList.stream().mapToInt(e -> e);
    }

    private List<Integer> sieve() {
        int[] sieve = new int[MAX_NUMBER]; // 0 for prime, 1 otherwise.
        List<Integer> primes = new ArrayList<>();

        sieve[0] = 1;
        sieve[1] = 1;

        // Iterate all numbers
        for (int p = 2; p < MAX_NUMBER; p++) {
            // if sirve[p] is checked by non prime, go next.
            if (sieve[p] == 1) {
                continue;
            }
            primes.add(p);
            // Set all multiples of current p, except p itself, as non-prime.
            for (int j = p + p; j < MAX_NUMBER; j += p) {
                sieve[j] = 1;
            }
        }

        return primes;
    }
}