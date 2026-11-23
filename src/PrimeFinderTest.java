import java.util.List;
import java.util.Optional;

public class PrimeFinderTest {
    public static void main(String[] args) {
        System.out.println("Запуск тестів PrimeFinder...");
        int passed = 0;
        int failed = 0;

        if (PrimeFinder.IS_PRIME.test(2)
                && PrimeFinder.IS_PRIME.test(3)
                && PrimeFinder.IS_PRIME.test(17)
                && !PrimeFinder.IS_PRIME.test(0)
                && !PrimeFinder.IS_PRIME.test(1)
                && !PrimeFinder.IS_PRIME.test(4)
                && !PrimeFinder.IS_PRIME.test(9)
                && !PrimeFinder.IS_PRIME.test(15)) {
            System.out.println("[OK] Тест IS_PRIME пройдено.");
            passed++;
        } else {
            System.err.println("[FAIL] Помилка в лямбді IS_PRIME!");
            failed++;
        }

        if (PrimeFinder.COUNT_BINARY_ZEROS.applyAsInt(2) == 1
                && PrimeFinder.COUNT_BINARY_ZEROS.applyAsInt(17) == 3
                && PrimeFinder.COUNT_BINARY_ZEROS.applyAsInt(31) == 0) {
            System.out.println("[OK] Тест COUNT_BINARY_ZEROS пройдено.");
            passed++;
        } else {
            System.err.println("[FAIL] Помилка в лямбді COUNT_BINARY_ZEROS!");
            failed++;
        }

        if (PrimeFinder.COUNT_BINARY_ONES.applyAsInt(2) == 1
                && PrimeFinder.COUNT_BINARY_ONES.applyAsInt(17) == 2
                && PrimeFinder.COUNT_BINARY_ONES.applyAsInt(31) == 5) {
            System.out.println("[OK] Тест COUNT_BINARY_ONES пройдено.");
            passed++;
        } else {
            System.err.println("[FAIL] Помилка в лямбді COUNT_BINARY_ONES!");
            failed++;
        }

        Optional<Integer> best20Zeros = PrimeFinder.findBestPrime(20, PrimeFinder.COUNT_BINARY_ZEROS);
        if (best20Zeros.isPresent() && best20Zeros.get() == 17) {
            System.out.println("[OK] Тест findBestPrime(20, zeros) пройдено.");
            passed++;
        } else {
            System.err.println("[FAIL] Помилка в findBestPrime(20, zeros)!");
            failed++;
        }

        Optional<Integer> best20Ones = PrimeFinder.findBestPrime(20, PrimeFinder.COUNT_BINARY_ONES);
        if (best20Ones.isPresent() && PrimeFinder.COUNT_BINARY_ONES.applyAsInt(best20Ones.get()) == 3) {
            System.out.println("[OK] Тест findBestPrime(20, ones) пройдено.");
            passed++;
        } else {
            System.err.println("[FAIL] Помилка в findBestPrime(20, ones)!");
            failed++;
        }

        List<PrimeFinder.PrimeInfo> best50 = PrimeFinder.findAllBestPrimes(50, PrimeFinder.COUNT_BINARY_ZEROS);
        List<Integer> numbers = best50.stream().map(PrimeFinder.PrimeInfo::number).toList();
        if (numbers.containsAll(List.of(17, 37, 41)) && numbers.size() == 3) {
            System.out.println("[OK] Тест findAllBestPrimes(50, zeros) пройдено.");
            passed++;
        } else {
            System.err.println("[FAIL] Помилка в findAllBestPrimes(50, zeros)!");
            failed++;
        }

        Optional<Integer> bestNegative = PrimeFinder.findBestPrime(1, PrimeFinder.COUNT_BINARY_ZEROS);
        if (bestNegative.isEmpty()) {
            System.out.println("[OK] Тест граничного випадку n < 2 пройдено.");
            passed++;
        } else {
            System.err.println("[FAIL] Помилка в обробці n < 2!");
            failed++;
        }

        System.out.println("----------------------------------------");
        System.out.println("Результат: Пройдено: " + passed + ", Помилок: " + failed);
        if (failed > 0) {
            System.exit(1);
        }
    }
}
