package com.company.output.format;

import com.company.work.BoxCombinationsResult;

import java.util.List;

/**
 * Класс для вывода
 */
public class SpaceOutputFormatter implements OutputFormatter {

    @Override
    public String format(BoxCombinationsResult data) {
        StringBuilder s = new StringBuilder();
        s.append(data.getPossibleCombinations());
        for (List<Integer> combination : data.getCombinations()) {
            s.append('\n');
            for (int i = 0; i < combination.size(); i++) {
                Integer integer = combination.get(i);
                s.append(integer);

                if (i != combination.size() - 1) {
                    s.append(" ");
                }
            }
        }
        return s.toString();
    }

}
