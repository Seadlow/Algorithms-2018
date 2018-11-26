package lesson6;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;

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
        int[][] matrix = new int[first.length() + 1][second.length() + 1];
        for (int i = 0; i < first.length(); i++)
            for (int j = 0; j < second.length(); j++)
                if (first.charAt(i) == second.charAt(j))
                    matrix[i + 1][j + 1] = matrix[i][j] + 1;
                else
                    matrix[i + 1][j + 1] =
                            Math.max(matrix[i + 1][j], matrix[i][j + 1]);
        StringBuilder answer = new StringBuilder();
        for (int x = first.length(), y = second.length();
             x != 0 && y != 0; ) {
            if (matrix[x][y] == matrix[x - 1][y])
                x--;
            else if (matrix[x][y] == matrix[x][y - 1])
                y--;
            else {
                if (first.charAt(x - 1) == second.charAt(y - 1)) {
                    answer.append(first.charAt(x - 1));
                    x--;
                    y--;
                }
            }
        }
        return answer.reverse().toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Средняя
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    // Трудоемкость O(N*M)
    // Ресурсоемкость O(N*M)
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
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
