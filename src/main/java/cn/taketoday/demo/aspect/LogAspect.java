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
package cn.taketoday.demo.aspect;

import org.aopalliance.intercept.Joinpoint;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

import cn.taketoday.aop.annotation.After;
import cn.taketoday.aop.annotation.AfterReturning;
import cn.taketoday.aop.annotation.AfterThrowing;
import cn.taketoday.aop.annotation.Annotated;
import cn.taketoday.aop.annotation.Argument;
import cn.taketoday.aop.annotation.Arguments;
import cn.taketoday.aop.annotation.Around;
import cn.taketoday.aop.annotation.Aspect;
import cn.taketoday.aop.annotation.Before;
import cn.taketoday.aop.annotation.JoinPoint;
import cn.taketoday.aop.annotation.Returning;
import cn.taketoday.aop.annotation.Throwing;
import cn.taketoday.context.Ordered;
import cn.taketoday.context.annotation.Component;
import cn.taketoday.context.annotation.Order;
import cn.taketoday.demo.domain.User;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Today <br>
 * 2018-11-06 17:52
 */
@Slf4j
@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE * 3)
public class LogAspect {

  @AfterReturning(Logger.class)
  public void afterReturning(@Returning Object returnValue) {
    log.debug("@AfterReturning returnValue: [{}]", returnValue);
//		int i = 1 / 0;
  }

  @AfterThrowing(Logger.class)
  public void afterThrowing(@Throwing Throwable throwable) {
    log.error("@AfterThrowing With Msg: [{}]", throwable.getMessage(), throwable);
  }

  /**
   * You can get all method argument use {@link Argument} or {@link Arguments}
   *
   * @param logger
   *         the annotation
   * @param joinpoint
   * @param user
   *         the parameter of the method
   */
  @Before(Logger.class)
  public void before(@Annotated Logger logger, @JoinPoint Joinpoint joinpoint, @Argument User user) {

    log.debug("@Before method in class with logger: [{}]", logger);
    AccessibleObject staticPart = joinpoint.getStaticPart();
    if (staticPart instanceof Method) {
      Method method = (Method) staticPart;
      if (method.getName().equals("login") && method.getParameterCount() != 0) {
        // user != null
        if (user != null) {
          log.debug("Login requested with user Id: [{}] and password: [{}]", user.getEmail(), user.getPassword());
        }
      }
    }
  }

  /**
   * You can get the return value and change it
   *
   * @param returnValue
   *         return value from method or join point
   *
   * @return
   */
  @After(Logger.class)
  public Object after(@Returning Object returnValue) {

    if (returnValue instanceof User) {
      log.debug("Current return value is: [{}]", returnValue);
      ((User) returnValue).setName("TODAY(changed)");
    }
    return returnValue;
  }

  @Around(Logger.class)
  public Object around(@JoinPoint Joinpoint joinpoint) throws Throwable {
    log.debug("@Around Before method");
    Object proceed = joinpoint.proceed();
    log.debug("@Around After method");
    return proceed;
  }

}
