package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String serialize(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public <T> T deserialize(String jsonString, Class<T> clazz) throws JsonMappingException, JsonProcessingException {
        return objectMapper.readValue(jsonString, clazz);
    }
}

