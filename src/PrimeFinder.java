import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class PrimeFinder {

    public static final IntPredicate IS_PRIME = number ->
            number >= 2 && IntStream.rangeClosed(2, (int) Math.sqrt(number))
                    .noneMatch(divisor -> number % divisor == 0);

    public static final IntUnaryOperator COUNT_BINARY_ZEROS = number ->
            (int) Integer.toBinaryString(number)
                    .chars()
                    .filter(ch -> ch == '0')
                    .count();

    public static final IntUnaryOperator COUNT_BINARY_ONES = number ->
            (int) Integer.toBinaryString(number)
                    .chars()
                    .filter(ch -> ch == '1')
                    .count();

    public record PrimeInfo(int number, String binaryString, int zerosCount, int onesCount) {
        public static PrimeInfo of(int prime) {
            String binary = Integer.toBinaryString(prime);
            int zeros = COUNT_BINARY_ZEROS.applyAsInt(prime);
            int ones = COUNT_BINARY_ONES.applyAsInt(prime);
            return new PrimeInfo(prime, binary, zeros, ones);
        }
    }

    public static List<Integer> getPrimes(int n) {
        if (n < 2) {
            return List.of();
        }
        return IntStream.rangeClosed(2, n)
                .filter(IS_PRIME)
                .boxed()
                .toList();
    }

    public static List<PrimeInfo> getPrimesInfo(int n) {
        if (n < 2) {
            return List.of();
        }
        return IntStream.rangeClosed(2, n)
                .filter(IS_PRIME)
                .mapToObj(PrimeInfo::of)
                .toList();
    }

    public static Optional<Integer> findBestPrime(int n, IntUnaryOperator metric) {
        if (n < 2) {
            return Optional.empty();
        }
        return IntStream.rangeClosed(2, n)
                .filter(IS_PRIME)
                .boxed()
                .max(Comparator.comparingInt(metric::applyAsInt));
    }

    public static List<PrimeInfo> findAllBestPrimes(int n, IntUnaryOperator metric) {
        if (n < 2) {
            return List.of();
        }

        int maxMetricValue = IntStream.rangeClosed(2, n)
                .filter(IS_PRIME)
                .map(metric)
                .max()
                .orElse(-1);

        if (maxMetricValue == -1) {
            return List.of();
        }

        return IntStream.rangeClosed(2, n)
                .filter(IS_PRIME)
                .filter(p -> metric.applyAsInt(p) == maxMetricValue)
                .mapToObj(PrimeInfo::of)
                .toList();
    }
}
