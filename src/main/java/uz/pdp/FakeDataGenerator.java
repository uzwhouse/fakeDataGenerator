package uz.pdp;

import uz.pdp.models.Request;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static uz.pdp.utils.RequestGenerator.*;
import static uz.pdp.utils.Utils.readConsole;
import static uz.pdp.utils.Utils.showFileType;

public class FakeDataGenerator {
    static {
        String key = "java.util.logging.config.file";
        String path = Objects.requireNonNull(FakeDataGenerator.class.getClassLoader().getResource("logging.properties")).getPath();
        System.setProperty(key, path);
    }

    public static void main(String[] args) {
        String fileName = readConsole("Enter File Name: ");
        Integer fieldCount = Integer.valueOf(readConsole("Enter Field Count: "));
        Set<Map<String, Object>> response = getResponse(fieldCount);
        generateFaker(new Request(fileName, response));
    }

    private static void generateFaker(Request request) {
        showFileType();
        switch (readConsole("Choice File Type: ")) {
            case "1" -> jsonData(request);
            case "2" -> csvData(request);
            case "3" -> sqlData(request);
            case "s" -> {
                System.out.println("STOP APPLICATION");
                System.exit(0);
            }
            default -> System.out.println("Invalid choice, please try again");
        }
        generateFaker(request);
    }

    private static void sqlData(Request request) {
        generatorSQLService.generate(request);
        System.out.println("SQL Data Successfully Generated)");
    }

    private static void csvData(Request request) {
        generatorCSVService.generate(request);
        System.out.println("CSV Data Successfully Generated");
    }

    private static void jsonData(Request request) {
        generatorJSONService.generate(request);
        System.out.println("JSON Data Successfully Generated");
    }
}