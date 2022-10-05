package com.company.output.print;

import java.io.IOException;

/**
 * Интерфейс для записи данных
 */
public interface Printer {
    void print(String content) throws IOException;
}
