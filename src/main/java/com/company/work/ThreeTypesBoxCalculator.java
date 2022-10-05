package com.company.work;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для нахождения способов купить ровно productQuantity килограмм товара в трех ящиках по a, b, c килограмм
 */
public class ThreeTypesBoxCalculator implements BoxCombinationsAlgorithm {

    private int a;
    private int b;
    private int c;
    private int productQuantity;

    /**
     *
     * @param values - массив содержащий данные о товаре:
     *               в первых трех элементах - a, b, c килограмм товара в каждом из трех ящиков
     *               в четвертом элементе - требуемое количество товара для покупки
     */
    public ThreeTypesBoxCalculator(int[] values) throws IllegalArgumentException {
        if (values.length != 4) {
            throw new IllegalArgumentException("Input must contain four numbers");
        }

        if (values[0] <= 0 || values[1] <= 0 || values[2] <= 0 || values[3] <= 0) {
            throw new IllegalArgumentException("All input parameters must be greater than zero");
        }

        this.a = values[0];
        this.b = values[1];
        this.c = values[2];
        this.productQuantity = values[3];
    }

    public BoxCombinationsResult compute() {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < productQuantity / a + 1; i++) {
            for (int j = 0; j < productQuantity / b + 1; j++) {
                int remainder = productQuantity - (i * a + j * b);
                if (remainder >= 0 && remainder % c == 0) {
                    result.add(List.of(i, j, remainder / c));
                }
            }
        }
        BoxCombinationsResult boxCombinationsResult = new BoxCombinationsResult();
        boxCombinationsResult.setPossibleCombinations(result.size());
        boxCombinationsResult.setCombinations(result);
        return boxCombinationsResult;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
