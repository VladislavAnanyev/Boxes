package com.company.input.parse;

import java.util.Arrays;

public class CommaInputParser implements InputParser {
    @Override
    public int[] parse(String inputData) {
        return Arrays.stream(
                inputData.split(",")
        ).mapToInt(Integer::parseInt).toArray();
    }
}
