# TODAY Framework Demo

> [TODAY Framework](https://github.com/TAKETODAY/today-framework) Demo


启动类：[cn.taketoday.demo.DemoApplication](src/main/java/cn/taketoday/demo/DemoApplication.java)
```java
public class DemoApplication 
      implements ApplicationListener<WebApplicationStartedEvent>, WebMvcConfiguration {

  @Value("#{site.server.appPath}")
  private String path;

  @GET("index/{q}")
  public String index(String q) {
    return q;
  }

  public static void main(String[] args) {
    WebApplication.run(DemoApplication.class, args);
  }
}
```
> 示例
- [普通参数注入示例](src/main/java/cn/taketoday/demo/controller/IndexController.java)
- [注解参数注入示例](src/main/java/cn/taketoday/demo/controller/AnnotationController.java)
- [登录示例](src/main/java/cn/taketoday/demo/controller/UserController.java)
- [文件上传示例](src/main/java/cn/taketoday/demo/controller/FileController.java)
- [RequestBody示例](src/main/java/cn/taketoday/demo/controller/RequestBodyController.java)
- [PathVariable示例](src/main/java/cn/taketoday/demo/controller/PathVariableController.java)
- [AOP示例](src/main/java/cn/taketoday/demo/aspect/LogAspect.java)
- [原型Controller示例](src/main/java/cn/taketoday/demo/controller/PrototypeController.java)
- [RedirectModel示例](src/main/java/cn/taketoday/demo/controller/RedirectModelController.java)
- [拦截器示例](src/main/java/cn/taketoday/demo/controller/InterceptorController.java)
- [StandardArgumentsController示例](src/main/java/cn/taketoday/demo/controller/StandardArgumentsController.java)
- [@ControllerAdvice示例](src/main/java/cn/taketoday/demo/config/ApplicationExceptionAdvice.java)
- [WebMvcConfiguration示例](src/main/java/cn/taketoday/demo/config/WebMvcConfig.java)
- [自定义参数转换器示例](src/main/java/cn/taketoday/demo/converter/DateConverter.java)
- [自定义参数解析器示例](src/main/java/cn/taketoday/demo/config/UserSessionParameterResolver.java)
- [自定义参数解析器示例](src/main/java/cn/taketoday/demo/config/PageableMethodArgumentResolver.java)
- [ResponseEntity](src/main/java/cn/taketoday/demo/controller/ResponseEntityController.java)
- ...


### 联系方式
- 邮箱 taketoday@foxmail.com

### 开源协议

请查看 [GNU GENERAL PUBLIC LICENSE](https://github.com/TAKETODAY/today-web-demo/blob/master/LICENSE)

