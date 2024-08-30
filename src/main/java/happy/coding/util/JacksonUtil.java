package happy.coding.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

public class JacksonUtil {
    static ObjectMapper objectMapper;
    static {
        objectMapper=new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
    }

    static public <T> T read(String jsonString,Class<T> type){
        try {
            return objectMapper.readValue(jsonString,type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    static public String write(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
