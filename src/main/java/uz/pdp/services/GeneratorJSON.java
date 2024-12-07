package uz.pdp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import uz.pdp.models.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static uz.pdp.utils.RequestGenerator.logger;

public class GeneratorJSON implements Generator {
    @Override
    public void generate(Request request) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(request.getFields());
            Files.writeString(Path.of("%s.json".formatted(request.getFileName())), jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            logger.warning("JSON writing failed :: " + e.getMessage());
        }
    }
}
