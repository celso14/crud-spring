package com.celso.crudspring.enums.utils;

import java.util.stream.Stream;

import com.celso.crudspring.enums.Category;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {

    @Override
    public String convertToDatabaseColumn(Category attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public Category convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(Category.values()).filter(c -> c.getValue().equals(dbData)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
