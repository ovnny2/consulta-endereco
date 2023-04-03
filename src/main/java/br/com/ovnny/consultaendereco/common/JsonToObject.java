package br.com.ovnny.consultaendereco.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonToObject {

    public static void configSerialization() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    }

    public static <T> T stringToEntity(Object input, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(input.toString(), clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Não foi possível converter a String para uma instância de " + clazz.getSimpleName());
        }
    }

    public static String entityToString(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String response = null;

        try {
            response = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return response;
    }
}