/*
 * LAB2. Задание 14.
 * Дана матрица A(n,n), элементы которой различны. Найти наибольший элемент
 * среди стоящих на главной и побочной диагоналях. Удалить все строки матрицы,
 * у которых количество двузначных чисел в строке равно заданному числу.
 */

import java.util.Scanner;

public class LAB2_Task14 {

    // Проверка, является ли число двузначным (по модулю от 10 до 99)
    private static boolean isTwoDigit(int number) {
        int abs = Math.abs(number);
        return abs >= 10 && abs <= 99;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер квадратной матрицы n: ");
        int n = scanner.nextInt();

        int[][] matrix = new int[n][n];
        System.out.println("Введите элементы матрицы (" + n + "x" + n + "), все элементы должны быть различны:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Поиск наибольшего элемента среди главной и побочной диагоналей
        int maxDiagonal = matrix[0][0];
        for (int i = 0; i < n; i++) {
            int mainDiag = matrix[i][i];
            int antiDiag = matrix[i][n - 1 - i];
            if (mainDiag > maxDiagonal) {
                maxDiagonal = mainDiag;
            }
            if (antiDiag > maxDiagonal) {
                maxDiagonal = antiDiag;
            }
        }
        System.out.println("\nНаибольший элемент среди главной и побочной диагоналей: " + maxDiagonal);

        System.out.print("\nВведите заданное количество двузначных чисел в строке для удаления: ");
        int k = scanner.nextInt();

        // Подсчёт двузначных чисел в каждой строке и определение строк для удаления
        boolean[] removeRow = new boolean[n];
        int rowsToKeep = 0;
        for (int i = 0; i < n; i++) {
            int twoDigitCount = 0;
            for (int j = 0; j < n; j++) {
                if (isTwoDigit(matrix[i][j])) {
                    twoDigitCount++;
                }
            }
            if (twoDigitCount == k) {
                removeRow[i] = true;
            } else {
                rowsToKeep++;
            }
        }

        int[][] result = new int[rowsToKeep][n];
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (!removeRow[i]) {
                result[r++] = matrix[i];
            }
        }

        System.out.println("\nИсходная матрица:");
        printMatrix(matrix);

        System.out.println("\nМатрица после удаления строк с " + k + " двузначными числами:");
        if (rowsToKeep == 0) {
            System.out.println("Все строки были удалены.");
        } else {
            printMatrix(result);
        }

        scanner.close();
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%5d", value);
            }
            System.out.println();
        }
    }
}
