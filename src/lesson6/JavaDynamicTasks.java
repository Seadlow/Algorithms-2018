package lesson6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    // Трудоемкость O(N*M)
    // Ресурсоемкость O(N*M)
    public static String longestCommonSubSequence(String first, String second) {
        int[][] matrix = buildMatrix(first, second);
        int i = first.length();
        int j = second.length();
        StringBuilder answer = new StringBuilder();
        while ((i > 0) && (j > 0)) {
            char f = first.charAt(i - 1);
            char s = second.charAt(j - 1);
            if (first.charAt(i - 1) == second.charAt(j - 1)) {
                answer.append(first.charAt(i - 1));
                i--;
                j--;
            } else if (matrix[i][j] == matrix[i - 1][j]) {
                i--;
            } else if (matrix[i][j] == matrix[i][j - 1]) {
                j--;
            }
        }
        return answer.reverse().toString();
    }

    private static int[][] buildMatrix(String first, String second) {
        int[][] matrix = new int[first.length() + 1][second.length() + 1];
        for (int i = 0; i <= first.length(); i++) {
            for (int j = 0; j <= second.length(); j++) {
                if ((i == 0) || (j == 0)) {
                    matrix[i][j] = 0;
                } else {
                    if (first.charAt(i - 1) == second.charAt(j - 1)) {
                        matrix[i][j] = matrix[i - 1][j - 1] + 1;
                    } else {
                        matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                    }
                }
            }
        }
        return matrix;
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    // Трудоемкость O(N^2)  Вложенный (двойной) цикл дает квадратичную трудоемкость
    // Ресурсоемкость O(N)
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        if (list.size() <= 1) {
            return list;
        }
        int[] m = new int[list.size()];
        for (int x = list.size() - 2; x >= 0; x--) {
            for (int y = list.size() - 1; y > x; y--) {
                if (list.get(x) < list.get(y) && m[x] <= m[y]) {
                    m[x]++;
                }
            }
        }
        int max = m[0];
        for (int i = 1; i < m.length; i++) {
            if (max < m[i]) {
                max = m[i];
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < m.length; i++) {
            if (m[i] == max) {
                result.add(list.get(i));
                max--;
            }
        }
        return result;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Сложная
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    //Трудоемкость - O(n * m)
    //Ресурсоемкость - O(n * m)
    //n - высота
    //m - ширина
    public static int shortestPathOnField(String inputName) throws IOException {
        String line;
        int result;
        int height, length;
        List<String> inputField = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputName))) {
            while ((line = bufferedReader.readLine()) != null) {
                inputField.add(line);
            }
        }
        length = inputField.get(0).split(" ").length;
        height = inputField.size();
        int sumField[][] = new int[height][length];
        sumField[0][0] = getIntegerValueByIndices(inputField, 0, 0);
        for (int i = 1; i < height; i++) {
            sumField[i][0] = getIntegerValueByIndices(inputField, i, 0) + sumField[i - 1][0];
        }
        for (int j = 1; j < length; j++) {
            sumField[0][j] = sumField[0][j - 1] + getIntegerValueByIndices(inputField, 0, j);
        }
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < length; j++) {
                sumField[i][j] = Math.min(sumField[i - 1][j - 1], Math.min(sumField[i][j - 1], sumField[i - 1][j]))
                        + getIntegerValueByIndices(inputField, i, j);
            }
        }
        result = sumField[height - 1][length - 1];
        return result;
    }

    private static int getIntegerValueByIndices(List<String> inputField, int i, int j) {
        return Integer.parseInt(inputField.get(i).split(" ")[j]);
    }
    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
