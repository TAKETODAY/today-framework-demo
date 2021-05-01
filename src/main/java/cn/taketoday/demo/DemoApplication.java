/**
 * Original Author -> 杨海健 (taketoday@foxmail.com) https://taketoday.cn
 * Copyright © TODAY & 2017 - 2020 All Rights Reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package cn.taketoday.demo;

import cn.taketoday.context.Ordered;
import cn.taketoday.context.annotation.Configuration;
import cn.taketoday.context.annotation.Import;
import cn.taketoday.context.annotation.Profile;
import cn.taketoday.context.annotation.Value;
import cn.taketoday.context.event.ApplicationListener;
import cn.taketoday.context.event.EventListener;
import cn.taketoday.demo.interceptor.LoginInterceptor;
import cn.taketoday.framework.ConfigurableWebServerApplicationContext;
import cn.taketoday.framework.WebApplication;
import cn.taketoday.framework.annotation.Starter;
import cn.taketoday.framework.server.TomcatServer;
import cn.taketoday.orm.mybatis.EnableDefaultMybatis;
import cn.taketoday.web.annotation.GET;
import cn.taketoday.web.annotation.RequestMapping;
import cn.taketoday.web.annotation.ResponseBody;
import cn.taketoday.web.config.WebMvcConfiguration;
import cn.taketoday.web.event.WebApplicationStartedEvent;
import cn.taketoday.web.registry.ResourceHandlerRegistry;
import cn.taketoday.web.resource.CacheControl;
import cn.taketoday.web.session.EnableWebSession;
import cn.taketoday.web.ui.ModelAndView;
import lombok.extern.slf4j.Slf4j;

/**
 * @author TODAY <br>
 * 2019-02-05 21:07
 */
@Slf4j
@Configuration
@ResponseBody
//@RestController
@EventListener
@RequestMapping
@EnableWebSession
//@EnableDefaultNetty
@EnableDefaultMybatis
@Import({ TomcatServer.class })
@Starter(webMvcConfigLocation = "web-mvc.xml")
//@ComponentScan({ "com.codahale" })
//@ServletSecurity(value = @HttpConstraint(value = EmptyRoleSemantic.PERMIT))
//@MultipartConfig(maxFileSize = 10240000, fileSizeThreshold = 1000000000, maxRequestSize = 1024000000)
public class DemoApplication implements ApplicationListener<WebApplicationStartedEvent>, WebMvcConfiguration {

  @Value("#{site.server.appPath}")
  private String path;

  @GET("index/{q}")
  public String index(String q) {
    return q;
  }

  public static void main(String[] args) {
    final ConfigurableWebServerApplicationContext context = WebApplication.run(DemoApplication.class, args);

    System.out.println(context);
  }

  @GET("index.script")
  public void index(ModelAndView modelAndView) {

    modelAndView.setContentType("text/html;charset=UTF-8");
    modelAndView.setView(new StringBuilder("<script>alert('2019');</script>"));
  }

  @Override
  public void onApplicationEvent(WebApplicationStartedEvent event) {
    log.info("----------------Application Started------------------");

  }

  @Profile("!prod")
  @GET("/swagger-resources/configuration/ui")
  public String swagger() {
    return "{\"apisSorter\":\"alpha\",\"deepLinking\":true,\"defaultModelExpandDepth\":1,\"defaultModelRendering\":\"EXAMPLE\",\"defaultModelsExpandDepth\":1,\"displayOperationId\":false,\"displayRequestDuration\":false,\"docExpansion\":\"NONE\",\"filter\":false,\"jsonEditor\":false,\"maxDisplayedTags\":null,\"operationsSorter\":\"ALPHA\",\"requestTimeout\":null,\"showExtensions\":false,\"showRequestHeaders\":false,\"supportedSubmitMethods\":[\"get\",\"put\",\"post\",\"delete\",\"options\",\"head\",\"patch\",\"trace\"],\"tagsSorter\":\"ALPHA\",\"validatorUrl\":\"\"}";
  }

  // swagger-resources/configuration/ui
  @Override
  public void configureResourceHandler(ResourceHandlerRegistry registry) {

    registry.addResourceMapping("/assets/**")//
            .enableGzip()//
            .gzipMinLength(10240)//
            .addLocations("classpath:/assets/");

    registry.addResourceMapping("/webjars/**")//
            .addLocations("classpath:/META-INF/resources/webjars/");

    registry.addResourceMapping("/swagger/**")//
            .addLocations("classpath:/META-INF/resources/");

    registry.addResourceMapping("/upload/**")//
            .addLocations("file:///" + path + "/upload/");

    registry.addResourceMapping("/favicon.ico")//
            .addLocations("classpath:/favicon.ico")//
            .cacheControl(CacheControl.noCache().cachePublic());

    registry.addResourceMapping(LoginInterceptor.class)//
            .setOrder(Ordered.HIGHEST_PRECEDENCE)//
            .setPathPatterns("/assets/admin/**")//
            .addLocations("classpath:/assets/admin/");
  }

  //    @Singleton
  //    public TomcatServer server() {
  //        TomcatServer tomcatServer = new TomcatServer();
  //
  //        Set<ErrorPage> errorPages = tomcatServer.getErrorPages();
  //
  //        errorPages.add(new ErrorPage(404, "/NotFound"));
  //        errorPages.add(new ErrorPage(403, "/Forbidden"));
  //        errorPages.add(new ErrorPage(400, "/BadRequest"));
  //        errorPages.add(new ErrorPage(500, "/ServerIsBusy"));
  //        errorPages.add(new ErrorPage(405, "/MethodNotAllowed"));
  //
  //        return tomcatServer;
  //    }

}
