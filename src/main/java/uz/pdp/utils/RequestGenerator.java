package uz.pdp.utils;

import uz.pdp.enums.FieldType;
import uz.pdp.models.Field;
import uz.pdp.services.Generator;
import uz.pdp.services.GeneratorCSV;
import uz.pdp.services.GeneratorJSON;
import uz.pdp.services.GeneratorSQL;

import java.util.*;
import java.util.logging.Logger;

import static uz.pdp.utils.Utils.*;

public class RequestGenerator {
    public static Generator generatorJSONService = new GeneratorJSON();
    public static Generator generatorCSVService = new GeneratorCSV();
    public static Generator generatorSQLService = new GeneratorSQL();
    public static Logger logger = Logger.getLogger(RequestGenerator.class.getName());

    public static Set<Map<String, Object>> getResponse(Integer count) {
        Set<Map<String, Object>> response = new LinkedHashSet<>();
        List<Field> fieldList = new LinkedList<>();
        String choiceFieldType = "";
        while (!choiceFieldType.equalsIgnoreCase("s")) {
            String fieldName = readConsole("Enter field name: ");
            showFieldType();
            FieldType fieldType = getFieldType(readConsole("Choice field type: "));
            fieldList.add(new Field(fieldName, fieldType));
            choiceFieldType = readConsole("Add Field -> y(es) or Stop Adding Fields -> s(top): ");
        }

        for (int i = 0; i < count; i++) {
            Map<String, Object> map = new LinkedHashMap<>();
            for (Field field : fieldList) {
                map.put(field.getFieldName(), getFieldFromFieldTypes.get(field.getFieldType()).get());
            }
            response.add(map);
        }
        return response;
    }
}
