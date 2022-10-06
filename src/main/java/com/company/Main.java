package com.company;

import com.company.work.TaskExecutor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        TaskExecutor taskExecutor = new TaskExecutor(args);
        try {
            taskExecutor.run();
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Data processing error: " + e.getClass().getSimpleName() + ": " + e.getMessage()
            );
        } catch (IOException e) {
            System.out.println(
                    "Error while writing/reading a file: " + e.getClass().getSimpleName() + ": " + e.getMessage()
            );
        }
    }
}


