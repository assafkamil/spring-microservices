package com.geekoosh.edu.cloud.vacations.users.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserTypeConverter implements AttributeConverter<UserType, String> {
    @Override
    public String convertToDatabaseColumn(UserType attribute) {
        if(attribute == UserType.GUEST)
            return "Not a user";
        return "Ma'man!";
    }

    @Override
    public UserType convertToEntityAttribute(String dbData) {
        if(dbData.equals("Not a user")) {return UserType.GUEST;}
        return UserType.PAID;
    }
}
