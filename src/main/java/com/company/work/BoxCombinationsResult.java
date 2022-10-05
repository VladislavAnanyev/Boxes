package com.company.work;

import java.util.List;

public class BoxCombinationsResult {

    private Integer possibleCombinations;
    private List<List<Integer>> combinations;

    public Integer getPossibleCombinations() {
        return possibleCombinations;
    }

    public void setPossibleCombinations(Integer possibleCombinations) {
        this.possibleCombinations = possibleCombinations;
    }

    public List<List<Integer>> getCombinations() {
        return combinations;
    }

    public void setCombinations(List<List<Integer>> combinations) {
        this.combinations = combinations;
    }
}
