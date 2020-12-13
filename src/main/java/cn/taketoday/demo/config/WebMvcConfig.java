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
 * along with this program.  If not, see [http://www.gnu.org/licenses/]
 */
package cn.taketoday.demo.config;

import cn.taketoday.context.Ordered;
import cn.taketoday.context.annotation.Autowired;
import cn.taketoday.context.annotation.Configuration;
import cn.taketoday.context.annotation.Env;
import cn.taketoday.context.annotation.Profile;
import cn.taketoday.context.annotation.Singleton;
import cn.taketoday.demo.controller.FunctionController;
import cn.taketoday.demo.interceptor.LoginInterceptor;
import cn.taketoday.web.annotation.EnableFunctionalHandling;
import cn.taketoday.web.annotation.EnableResourceHandling;
import cn.taketoday.web.config.WebMvcConfiguration;
import cn.taketoday.web.registry.FunctionHandlerRegistry;
import cn.taketoday.web.registry.ResourceHandlerRegistry;
import cn.taketoday.web.registry.ViewControllerHandlerRegistry;

/**
 * @author TODAY <br>
 * 2019-05-26 17:28
 */
@Configuration
//@EnableDefaultMybatis
//@EnableRedissonCaching
@EnableResourceHandling
@EnableFunctionalHandling
public class WebMvcConfig implements WebMvcConfiguration {

  @Singleton
  @Profile("dev")
  public ResourceHandlerRegistry devRsourceMappingRegistry(@Env("site.uploadPath") String upload,
                                                           @Env("site.assetsPath") String assetsPath) //
  {
    final ResourceHandlerRegistry registry = new ResourceHandlerRegistry();

    registry.addResourceMapping("/assets/**")//
            .addLocations(assetsPath);

    registry.addResourceMapping("/upload/**")//
            .addLocations(upload);

    registry.addResourceMapping("/logo.png")//
            .addLocations("file:///D:/dev/www.yhj.com/webapps/assets/images/logo.png");

    registry.addResourceMapping("/favicon.ico")//
            .addLocations("classpath:/favicon.ico");

    return registry;
  }

  @Singleton
  @Profile("prod")
  public ResourceHandlerRegistry prodResourceMappingRegistry() {

    final ResourceHandlerRegistry registry = new ResourceHandlerRegistry();

    registry.addResourceMapping(LoginInterceptor.class)//
            .setPathPatterns("/assets/admin/**")//
            .setOrder(Ordered.HIGHEST_PRECEDENCE)//
            .addLocations("/assets/admin/");

    return registry;
  }

//    @Override
//    public void configureResourceHandler(ResourceHandlerRegistry registry) {
//
//        registry.addResourceMapping(LoginInterceptor.class)//
//                .setPathPatterns("/assets/admin/**")//
//                .setOrder(Ordered.HIGHEST_PRECEDENCE)//
//                .addLocations("/assets/admin/");
//    }

  @Autowired
  private FunctionController functionController;

  @Override
  public void configureViewController(ViewControllerHandlerRegistry registry) {

    registry.addViewController("/github", "redirect:https://github.com");
    registry.addViewController("/login.do")
            .setResource("redirect:/login");
  }

  @Override
  public void configureFunctionHandler(FunctionHandlerRegistry registry) {

    registry.get("/function", functionController::function);
    registry.get("/function/test", functionController::test);
    registry.get("/function/script", functionController::script);

    registry.get("/function/error/500", (context) -> {
      context.sendError(500);
    });
  }

}
