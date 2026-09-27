/*
 * LAB2. Задание 4.
 * Дана матрица. Построить матрицу, полученную перестановкой столбцов
 * (первого с последним, второго с предпоследним и т. д.) из данной.
 */

import java.util.Scanner;

public class LAB2_Task4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt();

        int[][] matrix = new int[rows][cols];
        System.out.println("Введите элементы матрицы (" + rows + "x" + cols + "):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Строим новую матрицу: столбец j исходной матрицы становится
        // столбцом (cols-1-j) новой -> первый меняется с последним и т.д.
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix[i][cols - 1 - j];
            }
        }

        System.out.println("\nИсходная матрица:");
        printMatrix(matrix);

        System.out.println("\nМатрица с переставленными столбцами:");
        printMatrix(result);

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
