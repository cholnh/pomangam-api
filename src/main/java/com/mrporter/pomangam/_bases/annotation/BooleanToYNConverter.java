package com.mrporter.pomangam._bases.annotation;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

public class BooleanToYNConverter implements AttributeConverter<Boolean, String> {

    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? "Y" : "N";
    }

    public Boolean convertToEntityAttribute(String s) {
        return "Y".equals(s);
    }
}
