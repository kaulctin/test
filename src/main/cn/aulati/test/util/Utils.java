package cn.aulati.test.util;

import java.util.Arrays;
import java.util.List;

public class Utils {
    public static String matrixToString(int[][] mat) {
        return matrixToString(mat, false);
    }

    public static String matrixToString(int[][] mat, boolean oneRowOneLine) {
        if (mat == null) {
            return "";
        }

        int len = mat.length;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (mat.length > 0) {
            sb.append(Arrays.toString(mat[0]));
            for (int i = 1; i < len; i++) {
                if (oneRowOneLine) {
                    sb.append(System.lineSeparator());
                }
                sb.append(", ");
                sb.append(Arrays.toString(mat[i]));
            }
        } else {
            sb.append("[]");
        }
        sb.append("]");
        return sb.toString();
    }

    public static String listOfListToString(List<List<Integer>> list) {
        return listOfListToString(list, false);
    }

    public static String listOfListToString(List<List<Integer>> list, boolean oneRowOneLine) {
        if (list == null) {
            return "";
        }

        int len = list.size();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (len > 0) {
            sb.append(Arrays.toString(list.get(0).toArray(new Integer[0])));
            for (int i = 1; i < len; i++) {
                if (oneRowOneLine) {
                    sb.append(System.lineSeparator());
                }
                sb.append(", ");
                sb.append(Arrays.toString(list.get(i).toArray(new Integer[0])));
            }
        } else {
            sb.append("[]");
        }
        sb.append("]");
        return sb.toString();
    }
}
