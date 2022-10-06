package com.company.work;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для нахождения способов купить ровно productQuantity килограмм товара в трех ящиках по a, b, c килограмм
 */
public class ThreeTypesBoxCalculator implements BoxCalculator {

    /**
     * @param products - модель содержащая данные о килограммах товара в каждом из трех ящиков
     *                 и требуемое количество товара для покупки
     */
    public BoxCombinationsResult computeCombinations(Products products) {

        int[] quantityInEachOfTheThreeBoxes = products.getProductsQuantityInEachBox();

        if (quantityInEachOfTheThreeBoxes.length != 3 || products.getTotalProducts() == null) {
            throw new IllegalArgumentException("Information on three boxes and total amount of products must be provided");
        }

        int a = quantityInEachOfTheThreeBoxes[0];
        int b = quantityInEachOfTheThreeBoxes[1];
        int c = quantityInEachOfTheThreeBoxes[2];
        int productQuantity = products.getTotalProducts();

        if (a <= 0 || b <= 0 || c <= 0 || productQuantity <= 0) {
            throw new IllegalArgumentException("All input parameters must be greater than zero");
        }

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
}
