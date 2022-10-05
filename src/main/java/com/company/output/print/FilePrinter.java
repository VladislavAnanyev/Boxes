package com.company.output.print;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Класс для записи данных в файл
 */
public class FilePrinter implements Printer {

    private String filename;

    public FilePrinter() {
        this.filename = "output.txt";
    }
    public FilePrinter(String filename) {
        this.filename = filename;
    }

    @Override
    public void print(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.filename));
        writer.write(content);
        writer.close();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
