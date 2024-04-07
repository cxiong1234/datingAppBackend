package org.example.datingapp.Friendship.entities;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RequestStatusConverter implements AttributeConverter<RequestStatus, String> {

    @Override
    public String convertToDatabaseColumn(RequestStatus attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.toString();
    }

    @Override
    public RequestStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return RequestStatus.valueOf(dbData.charAt(0) + dbData.substring(1).toUpperCase());
    }
}
