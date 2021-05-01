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
package cn.taketoday.demo.interceptor;

import com.alibaba.fastjson.JSON;

import cn.taketoday.demo.Constant;
import cn.taketoday.demo.view.Json;
import cn.taketoday.web.RequestContext;
import cn.taketoday.web.interceptor.HandlerInterceptor;
import cn.taketoday.web.interceptor.WebSessionHandlerInterceptor;
import cn.taketoday.web.session.WebSessionManager;

/**
 * @author Today <br>
 * 2018-10-27 10:13
 */
public class LoginInterceptor extends WebSessionHandlerInterceptor implements HandlerInterceptor {

  public LoginInterceptor(WebSessionManager sessionManager) {
    super(sessionManager);
  }

  @Override
  public boolean beforeProcess(RequestContext requestContext, Object webMapping) throws Throwable {
    if (getAttribute(requestContext, Constant.USER_INFO) == null) {
      requestContext.getWriter().write(JSON.toJSONString(Json.failed().setCode(401).setMsg("Login Time Out")));
      return false;
    }
    return true;
  }

}
