package com.ufps.cedcufps.mapper;

import java.io.IOException;
import java.util.Map;
import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {

    @Override
    public String convertToDatabaseColumn(Map<String, Object> customerInfo) {
    	ObjectMapper objectMapper=new ObjectMapper();
        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(customerInfo);
        } catch (final JsonProcessingException e) {
            System.out.println("JSON writing error");
        }

        return customerInfoJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String customerInfoJSON) {
    	ObjectMapper objectMapper=new ObjectMapper();
        Map<String, Object> customerInfo = null;
        try {
        	if(customerInfoJSON != null) {
        		customerInfo = objectMapper.readValue(customerInfoJSON, Map.class);
        	}
            
        } catch (final IOException e) {
        	System.out.println("JSON reading error");
        }

        return customerInfo;
    }

}
