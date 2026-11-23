import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Лабораторна робота №7: Лямбда-вирази та Stream API ===");
        System.out.println("Завдання 7 (Simple Tasks): Серед простих чисел <= n знайти число");
        System.out.println("з максимальною кількістю нулів у двійковій формі.");
        System.out.println("---------------------------------------------------------");

        System.out.print("Введіть n: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Помилка: введіть коректне ціле число.");
            scanner.close();
            return;
        }

        int n = scanner.nextInt();

        if (n < 2) {
            System.out.println("Простих чисел у діапазоні до " + n + " не знайдено (найменше просте число - 2).");
            scanner.close();
            return;
        }

        Optional<Integer> bestPrimeZeros = PrimeFinder.findBestPrime(n, PrimeFinder.COUNT_BINARY_ZEROS);

        if (bestPrimeZeros.isPresent()) {
            int prime = bestPrimeZeros.get();
            String binary = Integer.toBinaryString(prime);
            int zerosCount = PrimeFinder.COUNT_BINARY_ZEROS.applyAsInt(prime);

            System.out.println("\n[Результат Завдання 7: Максимальна кількість нулів]");
            System.out.println("Шукане просте число: " + prime);
            System.out.println("Двійкова форма:      " + binary);
            System.out.println("Кількість нулів:     " + zerosCount);

            List<PrimeFinder.PrimeInfo> allBest = PrimeFinder.findAllBestPrimes(n, PrimeFinder.COUNT_BINARY_ZEROS);
            if (allBest.size() > 1) {
                System.out.println("\nВсі прості числа до " + n + ", що мають таку максимальну кількість нулів (" + zerosCount + "):");
                allBest.forEach(info ->
                        System.out.printf(" - %d (двійкове: %s, нулів: %d)%n",
                                info.number(), info.binaryString(), info.zerosCount())
                );
            }
        }

        Optional<Integer> bestPrimeOnes = PrimeFinder.findBestPrime(n, PrimeFinder.COUNT_BINARY_ONES);
        if (bestPrimeOnes.isPresent()) {
            int prime = bestPrimeOnes.get();
            String binary = Integer.toBinaryString(prime);
            int onesCount = PrimeFinder.COUNT_BINARY_ONES.applyAsInt(prime);

            System.out.println("\n[Для порівняння з Лаб 1 (максимальна кількість одиниць)]");
            System.out.println("Шукане просте число: " + prime);
            System.out.println("Двійкова форма:      " + binary);
            System.out.println("Кількість одиниць:   " + onesCount);
        }

        if (n <= 100) {
            System.out.println("\n--- Таблиця всіх простих чисел у діапазоні до " + n + " ---");
            System.out.printf("%-8s | %-12s | %-10s | %-10s%n", "Число", "Двійкове", "Нулів", "Одиниць");
            System.out.println("-------------------------------------------------");
            PrimeFinder.getPrimesInfo(n).forEach(info ->
                    System.out.printf("%-8d | %-12s | %-10d | %-10d%n",
                            info.number(), info.binaryString(), info.zerosCount(), info.onesCount())
            );
        }

        scanner.close();
    }
}
