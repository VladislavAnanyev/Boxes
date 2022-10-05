package com.company.work;

import com.company.input.parse.CommaInputParser;
import com.company.input.parse.InputParser;
import com.company.input.read.FileReader;
import com.company.input.read.Reader;
import com.company.output.format.OutputFormatter;
import com.company.output.format.SpaceOutputFormatter;
import com.company.output.print.FilePrinter;
import com.company.output.print.Printer;

import java.io.IOException;

public class TaskExecutor {
    private Reader reader;
    private String inputData;
    private InputParser inputParser;
    private BoxCombinationsAlgorithm boxCombinationsAlgorithm;
    private BoxCombinationsResult result;
    private OutputFormatter outputFormatter;
    private Printer printer;

    public TaskExecutor() {
        this.reader = new FileReader();
        this.inputParser = new CommaInputParser();
        this.outputFormatter = new SpaceOutputFormatter();
        this.printer = new FilePrinter();
    }

    public TaskExecutor(Reader reader, InputParser inputParser, BoxCombinationsAlgorithm boxCombinationsAlgorithm,
                        OutputFormatter outputFormatter, Printer printer) {

        if (reader == null) {
            throw new IllegalArgumentException("reader required");
        }

        if (inputParser == null) {
            throw new IllegalArgumentException("inputParser required");
        }

        if (printer != null && outputFormatter == null) {
            throw new IllegalArgumentException("outputFormatter required when printer is present");
        }

        this.reader = reader;
        this.inputParser = inputParser;
        this.boxCombinationsAlgorithm = boxCombinationsAlgorithm;
        this.outputFormatter = outputFormatter;
        this.printer = printer;
    }

    public TaskExecutor(String inputData, BoxCombinationsAlgorithm boxCombinationsAlgorithm,
                        BoxCombinationsResult result, OutputFormatter outputFormatter, Printer printer) {

        if (inputData == null) {
            throw new IllegalArgumentException("inputData required");
        }

        if (printer != null && outputFormatter == null) {
            throw new IllegalArgumentException("outputFormatter required when printer is present");
        }

        this.inputData = inputData;
        this.boxCombinationsAlgorithm = boxCombinationsAlgorithm;
        this.result = result;
        this.outputFormatter = outputFormatter;
        this.printer = printer;
    }

    public void run(String[] args) throws IOException {
        this.processArguments(args);
        this.readInputData();
        int[] parsedData = this.parseInputData(this.inputData);
        this.compute(
                boxCombinationsAlgorithm == null ? new ThreeTypesBoxCalculator(parsedData) : boxCombinationsAlgorithm
        );
        this.print(this.result);
    }

    public BoxCombinationsResult getResult() {
        return result;
    }

    private void processArguments(String[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-in")) {
                this.reader = new FileReader(args[i + 1]);
            }
            if (args[i].equals("-out")) {
                this.printer = new FilePrinter(args[i + 1]);
            }
        }
    }

    private void readInputData() throws IOException {
        if (reader != null) {
            this.inputData = this.reader.read();
        }
    }

    private int[] parseInputData(String inputData) {
        return inputParser.parse(inputData);
    }

    private void compute(BoxCombinationsAlgorithm algorithm) {
        this.boxCombinationsAlgorithm = algorithm;
        this.result = this.boxCombinationsAlgorithm.compute();
    }

    private void print(BoxCombinationsResult result) throws IOException {
        if (printer != null) {
            this.printer.print(
                    this.outputFormatter
                            .format(result)
            );
        }
    }
}
