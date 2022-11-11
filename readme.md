# QueryWarpperDemo
此代码主要用于复现mybatis-plus的QueryWarpper查询不出结果的情况

Issue URL:[https://github.com/baomidou/mybatis-plus/issues/4918](https://github.com/baomidou/mybatis-plus/issues/4918)

此问题的主要原因是自己写的<kbd>@TableField(typeHandler = ObjectToJson.class)</kbd>存在解析问题，导致QueryWarpper查询不出结果的情况

下方是<kbd>ObjectToJson.class</kbd>代码：

```java
@Component
public class ObjectToJson extends BaseTypeHandler<Object> {
    @Resource
    private ObjectMapper objectMapper;

    private <T> String ObjectToJson(T o){
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new ControllerException(RCode.SYSTEM_ERROR,e.getMessage());
        }
    }

    private <T> T jsonToObject(String json, TypeReference<T> typeReference){
        if (json==null)return null;
        try {
            return objectMapper.readValue(json,typeReference);
        }catch (JsonProcessingException e) {
            throw new ControllerException(RCode.SYSTEM_ERROR,e.getMessage());
        }
    }



    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        String json=ObjectToJson(o);
        preparedStatement.setString(i,json);
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String str=resultSet.getString(s);
        return jsonToObject(str, new TypeReference<>() {
        });
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String str=resultSet.getString(i);
        return jsonToObject(str, new TypeReference<>() {
        });
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String str=callableStatement.getString(i);
        return jsonToObject(str, new TypeReference<>() {
        });
    }
}
```

官方指定提供<kbd>JacksonTypeHandler.class</kbd>解析

本项目中使用的是<kbd>@TableField(typeHandler = JacksonTypeHandler.class)</kbd>

>使用原生mybatis-plus可以正常得到结果

尝试添加druid来靠近接近配置相关配置

>使用druid+mybatis-plus也可以正常得到结果

返现过程中出现的2个问题，需要确保<kbd>main/java</kbd>下的目录和<kbd>test/java</kbd>下的目录保持一致。
```shell
├── main
│   ├── java
│       └── redcrazyghost
│           └── querywarpperdemo
│               └── QueryWarpperDemoApplication.java
└─ test
   └── java
       └── redcrazyghost
           └── querywarpperdemo
               └── QueryWarpperTest.java
```

>### 1.使用@SpringBootTest
>
>```java
>java.lang.IllegalStateException: Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test
>
>	at org.springframework.util.Assert.state(Assert.java:76)
>	at org.springframework.boot.test.context.SpringBootTestContextBootstrapper.getOrFindConfigurationClasses(SpringBootTestContextBootstrapper.java:236)
>	at org.springframework.boot.test.context.SpringBootTestContextBootstrapper.processMergedContextConfiguration(SpringBootTestContextBootstrapper.java:152)
>	at org.springframework.test.context.support.AbstractTestContextBootstrapper.buildMergedContextConfiguration(AbstractTestContextBootstrapper.java:393)
>	at org.springframework.test.context.support.AbstractTestContextBootstrapper.buildDefaultMergedContextConfiguration(AbstractTestContextBootstrapper.java:309)
>	at org.springframework.test.context.support.AbstractTestContextBootstrapper.buildMergedContextConfiguration(AbstractTestContextBootstrapper.java:262)
>	at org.springframework.test.context.support.AbstractTestContextBootstrapper.buildTestContext(AbstractTestContextBootstrapper.java:107)
>	at org.springframework.boot.test.context.SpringBootTestContextBootstrapper.buildTestContext(SpringBootTestContextBootstrapper.java:102)
>```
>
>### 2.使用@SpringBootTest(classes= redcrazyghost.querywarpperdemo.QueryWarpperTest.class)
>
>```Java
>java.lang.IllegalStateException: Failed to load ApplicationContext
>
>	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:132)
>	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:124)
>	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:118)
>	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:83)
>	at org.springframework.boot.test.autoconfigure.SpringBootDependencyInjectionTestExecutionListener.prepareTestInstance(SpringBootDependencyInjectionTestExecutionListener.java:43)
>	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:248)
>	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:138)
>```
