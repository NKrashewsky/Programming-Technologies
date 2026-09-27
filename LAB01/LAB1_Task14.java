/*
 * LAB1. Задание 14.
 * Найти первое простое число n в заданной последовательности натуральных чисел
 * с максимальной суммой цифр.
 */

import java.util.Scanner;

public class LAB1_Task14 {

    // Проверка числа на простоту
    private static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; (long) i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Сумма цифр числа
    private static int digitSum(int number) {
        int sum = 0;
        int num = Math.abs(number);
        while (num > 0) {
            sum += num % 10;
            num /= 10;
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

        int result = -1;
        int maxDigitSum = -1;

        // Идём по последовательности, среди простых чисел ищем то,
        // у которого сумма цифр максимальна; при равенстве сумм
        // оставляем то, что встретилось первым 
        for (int number : sequence) {
            if (isPrime(number)) {
                int s = digitSum(number);
                if (s > maxDigitSum) {
                    maxDigitSum = s;
                    result = number;
                }
            }
        }

        if (result == -1) {
            System.out.println("\nВ последовательности нет простых чисел.");
        } else {
            System.out.println("\nПервое простое число с максимальной суммой цифр: " + result);
            System.out.println("Сумма цифр этого числа: " + maxDigitSum);
        }

        scanner.close();
    }
}
