package uz.pdp.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import uz.pdp.models.Request;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static uz.pdp.utils.RequestGenerator.logger;

public class GeneratorCSV implements Generator {
    @Override
    public void generate(Request request) {
        try (CSVPrinter printer = new CSVPrinter(new FileWriter("%s.csv".formatted(request.getFileName())),
                CSVFormat.DEFAULT.withHeader(request.getFields().iterator().next().keySet().toArray(new String[0])))) {
            for (Map<String, Object> record : request.getFields()) {
                printer.printRecord(record.values());
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.warning("CSV writing failed :: " + e.getMessage());
        }
    }
}
