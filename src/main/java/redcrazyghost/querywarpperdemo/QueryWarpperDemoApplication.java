package redcrazyghost.querywarpperdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author RedCrazyGhost - wenxingzhan
 * @date 2022/11/03 12:09
 **/
@SpringBootApplication
@MapperScan("redcrazyghost.querywarpperdemo.mapper")
public class QueryWarpperDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(QueryWarpperDemoApplication.class,args);
    }
}
