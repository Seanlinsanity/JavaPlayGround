package com.seanlindev.algorithms;

import java.util.List;

public class ThreeDimensionSurfaceArea {
    public static int surfaceArea(List<List<Integer>> A) {
        int result = 0;
        for (int i = 0; i < A.size(); i++) {
            //TOP, DOWN
            result += 2 * A.get(i).size();

            //LEFT
            if (i == 0) {
                result += A.get(i).stream().reduce(0, (sum, item) -> {return sum + item;});
            } else {
                for (int j = 0; j < A.get(i).size(); j++) {
                    if (A.get(i).get(j) > A.get(i - 1).get(j)) {
                        result += (A.get(i).get(j) - A.get(i - 1).get(j));
                    }
                }
            }

            //RIGHT
            if (i == A.size() - 1) {
                result += A.get(i).stream().reduce(0, (sum, item) -> {return sum + item;});
            } else {
                for (int j = 0; j < A.get(i).size(); j++) {
                    if (A.get(i).get(j) > A.get(i + 1).get(j)) {
                        result += (A.get(i).get(j) - A.get(i + 1).get(j));
                    }
                }
            }

            //FRONT
            for (int j = 0; j < A.get(i).size(); j++) {
                if (j == 0) {
                    result += A.get(i).get(j);
                } else if (A.get(i).get(j) - A.get(i).get(j - 1) > 0) {
                    result += (A.get(i).get(j) - A.get(i).get(j - 1));
                }
            }

            //BACK
            for (int j = 0; j < A.get(i).size(); j++) {
                if (j == A.get(i).size() - 1) {
                    result += A.get(i).get(j);
                } else if (A.get(i).get(j) - A.get(i).get(j + 1) > 0) {
                    result += (A.get(i).get(j) - A.get(i).get(j + 1));
                }
            }
        }

        return result;

    }
}
