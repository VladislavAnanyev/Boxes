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
    private InputParser inputParser;
    private BoxCalculator calculator;
    private OutputFormatter outputFormatter;
    private Printer printer;
    private BoxCombinationsResult result;

    public TaskExecutor(String[] args) {
        processArguments(args);

        if (this.reader == null) {
            this.reader = new FileReader();
        }

        if (this.printer == null) {
            this.printer = new FilePrinter();
        }

        this.inputParser = new CommaInputParser();
        this.calculator = new ThreeTypesBoxCalculator();
        this.outputFormatter = new SpaceOutputFormatter();
    }

    public TaskExecutor(Reader reader, InputParser inputParser, BoxCalculator calculator,
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

        if (calculator == null) {
            throw new IllegalArgumentException("calculator required");
        }

        this.reader = reader;
        this.inputParser = inputParser;
        this.calculator = calculator;
        this.outputFormatter = outputFormatter;
        this.printer = printer;
    }

    public void run() throws IOException {
        Products products = findOutProductsInfo();
        this.result = calculator.computeCombinations(products);
        write(this.result);
    }

    public BoxCombinationsResult getResult() {
        return result;
    }

    private Products findOutProductsInfo() throws IOException {
        String data = reader.read();
        int[] parsedValues = inputParser.parse(data);
        return mapToProducts(parsedValues);
    }

    private Products mapToProducts(int[] parsedValues) {
        Products products = new Products();
        products.setTotalProducts(parsedValues[parsedValues.length - 1]);

        int[] quantitiesInEachBox = new int[parsedValues.length - 1];
        System.arraycopy(parsedValues, 0, quantitiesInEachBox, 0, parsedValues.length - 1);
        products.setProductsQuantityInEachBox(quantitiesInEachBox);
        return products;
    }

    private void write(BoxCombinationsResult result) throws IOException {
        if (printer != null) {
            printer.print(
                    outputFormatter.format(result)
            );
        }
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
}
