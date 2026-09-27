/*
 * LAB1. Задание 4.
 * Найти все p-значные числа из заданной последовательности натуральных чисел,
 * сумма цифр которых равна заданному числу k, и подсчитать их количество.
 */

import java.util.Scanner;

public class LAB1_Task4 {

    // Проверка: является ли число p-значным (содержит ровно p цифр)
    private static boolean isPDigit(int number, int p) {
        int num = Math.abs(number);
        int count = (num == 0) ? 1 : 0;
        while (num > 0) {
            count++;
            num /= 10;
        }
        return count == p;
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

        System.out.print("Введите количество значащих цифр p: ");
        int p = scanner.nextInt();

        System.out.print("Введите требуемую сумму цифр k: ");
        int k = scanner.nextInt();

        System.out.println("\nНайденные p-значные числа с суммой цифр, равной k:");
        int count = 0;
        for (int number : sequence) {
            if (isPDigit(number, p) && digitSum(number) == k) {
                System.out.print(number + " ");
                count++;
            }
        }
        if (count == 0) {
            System.out.print("таких чисел не найдено");
        }

        System.out.println("\n\nКоличество найденных чисел: " + count);

        scanner.close();
    }
}
