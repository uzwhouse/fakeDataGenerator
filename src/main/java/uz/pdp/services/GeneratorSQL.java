package uz.pdp.services;

import uz.pdp.models.Request;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Map;
import java.util.StringJoiner;

import static uz.pdp.utils.RequestGenerator.logger;

public class GeneratorSQL implements Generator {
    @Override
    public void generate(Request request) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("%s.sql", request.getFileName())))) {
            for (Map<String, Object> map : request.getFields()) {
                StringJoiner keyJoiner = new StringJoiner(", ");
                StringJoiner valueJoiner = new StringJoiner(", ");

                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    keyJoiner.add(entry.getKey());
                    valueJoiner.add(String.valueOf(entry.getValue()));
                }
                String sql = String.format("INSERT INTO %s (%s) VALUES (%s);",
                        request.getFileName(), keyJoiner, valueJoiner);
                writer.write(sql);
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("SQL writing failed :: " + e.getMessage());
        }
    }
}
