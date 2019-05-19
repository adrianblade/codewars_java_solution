package kyu3.prime_streaming_pg_13;

import java.util.stream.IntStream;

public class Pg13PrimeStreaming {

    private static final int LIMIT = 15 * 1000 * 1000 + 100000 * 5;

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
}