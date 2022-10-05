package com.company;


import com.company.input.parse.CommaInputParser;
import com.company.input.parse.InputParser;
import com.company.output.format.OutputFormatter;
import com.company.output.format.SpaceOutputFormatter;
import com.company.work.ThreeTypesBoxCalculator;
import com.company.work.BoxCombinationsResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    public void calculateTest() {
        ThreeTypesBoxCalculator threeTypesBoxCalculator = new ThreeTypesBoxCalculator(new int[]{15, 17, 21, 185});
        BoxCombinationsResult calculate = threeTypesBoxCalculator.compute();
        List<List<Integer>> actual = calculate.getCombinations();
        List<List<Integer>> expected = List.of(
                List.of(0, 1, 8),
                List.of(1, 10, 0),
                List.of(3, 7, 1),
                List.of(5, 4, 2),
                List.of(7, 1, 3)
        );
        assertEquals(5, calculate.getPossibleCombinations());
        assertEquals(expected, actual);
    }

    @Test
    public void calculateWithWrongDataTest() {
        try {
            new ThreeTypesBoxCalculator(new int[]{15, 17, 21, 185, 1});
            throw new RuntimeException();
        } catch (IllegalArgumentException e) {
            assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void parseInputDataTest() {
        String inputData = "1,2,3,4";
        InputParser inputParser = new CommaInputParser();
        assertArrayEquals(new int[]{1, 2, 3, 4}, inputParser.parse(inputData));
    }

    @Test
    public void parseInputWithWrongDataTest() {
        String inputData = "1,2,3,4ю";
        InputParser inputParser = new CommaInputParser();
        try {
            inputParser.parse(inputData);
            throw new RuntimeException();
        } catch (IllegalArgumentException e) {
            assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void parseInputWithBlankDataTest() {
        String inputData = "";
        InputParser inputParser = new CommaInputParser();
        try {
            inputParser.parse(inputData);
            throw new RuntimeException();
        } catch (IllegalArgumentException e) {
            assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void formatDataTest() {
        BoxCombinationsResult result = new BoxCombinationsResult();
        result.setPossibleCombinations(5);
        result.setCombinations(
                List.of(
                        List.of(0, 1, 8),
                        List.of(1, 10, 0),
                        List.of(3, 7, 1),
                        List.of(5, 4, 2),
                        List.of(7, 1, 3)
                )
        );

        String expectedOutput =
                """
                        5
                        0 1 8
                        1 10 0
                        3 7 1
                        5 4 2
                        7 1 3""";

        OutputFormatter outputFormatter = new SpaceOutputFormatter();
        assertEquals(expectedOutput, outputFormatter.format(result));
    }
}
