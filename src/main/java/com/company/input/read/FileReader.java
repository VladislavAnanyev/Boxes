package com.company.input.read;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Класс для чтения данных из файла
 */
public class FileReader implements Reader {

    private String filename;

    public FileReader() {
        this.filename = "input.txt";
    }

    public FileReader(String filename) {
        this.filename = filename;
    }

    @Override
    public String read() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String fileContent = reader.readLine();
            return fileContent != null ? fileContent : "";
        }
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
