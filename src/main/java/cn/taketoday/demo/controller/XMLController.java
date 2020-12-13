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
package cn.taketoday.demo.controller;

import cn.taketoday.context.annotation.Autowired;
import cn.taketoday.context.utils.StringUtils;
import cn.taketoday.demo.service.UserService;
import cn.taketoday.web.RequestContext;

/**
 * @author Today <br>
 *
 * 2018-12-08 23:15
 */
public class XMLController {

  @Autowired
  private UserService userService;

  public void test(RequestContext request) {

    System.err.println(userService);
    userService.login(null);
    request.attribute("key", "World");
    System.err.println(request);
  }

  public Object obj(RequestContext request) {

    String key = request.parameter("r");
    if (StringUtils.isNotEmpty(key)) {
      return "redirect:/" + key;
    }
    request.attribute("key", request.parameter("key"));

    return "/xml/test";
  }

}
