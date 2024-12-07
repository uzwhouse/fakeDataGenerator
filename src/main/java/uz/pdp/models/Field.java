package uz.pdp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.enums.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Field {
    private String fieldName;
    private FieldType fieldType;
}