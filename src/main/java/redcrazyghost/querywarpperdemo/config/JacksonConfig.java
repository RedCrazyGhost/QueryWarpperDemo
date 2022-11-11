package redcrazyghost.querywarpperdemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jackson配置
 *
 * @author RedCrazyGhost - wenxingzhan
 * @date 2022/10/10 15:56
 **/
@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper=new ObjectMapper();
//        objectMapper.registerModule(new Java());
        return objectMapper;
    }
}
