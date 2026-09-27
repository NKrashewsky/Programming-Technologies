/*
 * LAB1. Задание 34.
 * Найти число n из заданной последовательности чисел с максимальной суммой
 * своих простых делителей, включая их в сумму по одному разу.
 */

import java.util.Scanner;

public class LAB1_Task34 {

    // Сумма различных простых делителей числа (каждый делитель учитывается один раз)
    private static int sumOfDistinctPrimeDivisors(int number) {
        int num = Math.abs(number);
        int sum = 0;
        for (int i = 2; (long) i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                while (num % i == 0) {
                    num /= i;
                }
            }
        }
        if (num > 1) {
            sum += num; // оставшийся простой множитель (если он больше 1)
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество элементов последовательности: ");
        int n = scanner.nextInt();

        int[] sequence = new int[n];
        System.out.println("Введите " + n + " натуральных чисел:");
        for (int i = 0; i < n; i++) {
            sequence[i] = scanner.nextInt();
        }

        int result = sequence[0];
        int maxSum = sumOfDistinctPrimeDivisors(result);

        for (int number : sequence) {
            int s = sumOfDistinctPrimeDivisors(number);
            if (s > maxSum) {
                maxSum = s;
                result = number;
            }
        }

        System.out.println("\nЧисло с максимальной суммой различных простых делителей: " + result);
        System.out.println("Сумма его простых делителей: " + maxSum);

        scanner.close();
    }
}
