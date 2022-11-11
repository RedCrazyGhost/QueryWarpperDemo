package redcrazyghost.querywarpperdemo.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * MybatisPlus配置
 *
 * @author RedCrazyGhost - wenxingzhan
 * @date 2022/10/09 16:03
 **/
@Configuration
public class MybatisPlusConfiguration implements CommandLineRunner {
    @Resource
    private ObjectMapper objectMapper;

    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件，如果你不配置，分页插件将不生效
        // 指定数据库方言为 MYSQL
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Override
    public void run(String... args) throws Exception {
        JacksonTypeHandler.setObjectMapper(objectMapper);
    }
}
