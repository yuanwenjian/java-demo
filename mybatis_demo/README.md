# Spring-mybatis项目demo
## 配置mybatis 
1中方式config-location指定xml文件配置，

另一种在application.yml中配置

config-location 与configuration 不能同时使用
```properties
mybatis:
    configuration:
        map-underscore-to-camel-case: true
```

## 指定mapper接口
1. 在单个文件使用@Mapper注解
2. 使用@MapperScan()指定mapper包所在路径