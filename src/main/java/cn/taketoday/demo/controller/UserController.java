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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import cn.taketoday.context.annotation.Autowired;
import cn.taketoday.demo.aspect.Logger;
import cn.taketoday.demo.domain.User;
import cn.taketoday.demo.interceptor.LoginInterceptor;
import cn.taketoday.demo.service.UserService;
import cn.taketoday.web.RequestContext;
import cn.taketoday.web.RequestMethod;
import cn.taketoday.web.annotation.ActionMapping;
import cn.taketoday.web.annotation.Controller;
import cn.taketoday.web.annotation.GET;
import cn.taketoday.web.annotation.Interceptor;
import cn.taketoday.web.annotation.ResponseBody;
import cn.taketoday.web.ui.RedirectModel;

/**
 * @author Today <br>
 * 2018-10-27 10:09
 */
@Controller
public class UserController extends BaseController {

  private static final long serialVersionUID = -1;

  @Autowired
  private UserService userService;

  @GET("/login")
  @Logger("登录界面")
  public String login() {
    return "/login/login";
  }

  @Logger("登录")
//	@POST("/login")
  @ActionMapping(value = "/login", method = RequestMethod.POST)
  public String login(HttpSession session, User user, RedirectModel redirectModel) {
//    public String login(User user, RedirectModel redirectModel) {

    User login = userService.login(user);
    if (login == null) {
      redirectModel.setAttribute("userId", user.getEmail());
      redirectModel.setAttribute("msg", "登录失败");
      return "redirect:/login";
    }
    redirectModel.setAttribute("msg", "登录成功");
    session.setAttribute(USER_INFO, login);
    return "redirect:/user/info";
  }

  @Logger("注册界面")
//	@ResponseStatus(value = 500, msg = "ERROR")
  @ActionMapping(value = "/register", method = RequestMethod.GET)
  public String register() {
    return "/register/register";
  }

  @Logger("注册")
  @ActionMapping(value = "/register", method = RequestMethod.POST)
  public String register(RequestContext request, User user) {

    userService.register(user);

    return "redirect:/login";
  }

  @ActionMapping(value = "/user/info", method = RequestMethod.GET)
  @Interceptor(LoginInterceptor.class)
  public String user(RequestContext request) {
    request.setAttribute("msg", "用户信息 ");
    return "/user/info";
  }

  @Interceptor(LoginInterceptor.class)
  @ActionMapping(value = "/user/list", method = RequestMethod.GET)
  public String list(RequestContext request, List<User> user) {
    request.setAttribute("msg", "用户信息");
    request.setAttribute("users", user);
    return "/user/list";
  }

  @ActionMapping(value = "/user/add", method = RequestMethod.GET)
  public String add(RequestContext request) {
    request.setAttribute("msg", "添加用户");
    request.setAttribute("max", 5);
    return "/user/add";
  }

  @ResponseBody
  @ActionMapping(value = "/user/add", method = RequestMethod.POST)
  public String add(List<User> users) {
    System.out.println(users);
    return users.toString();
  }

  @ActionMapping(value = "/user/map", method = RequestMethod.GET)
  public String addMap(RequestContext request) {
    request.setAttribute("msg", "添加用户");
    return "/user/map";
  }

  @ResponseBody
  @ActionMapping(value = "/user/map", method = RequestMethod.POST)
  public String add(Map<String, User> users) {
    System.out.println(users);
    return users.toString();
  }

//    @ActionMapping(value = "/logout", method = RequestMethod.GET)
//    public String logout(HttpSession session) {
//
//        session.invalidate();
//        return "redirect:/login";
//    }

  @ActionMapping(value = "/user/date", method = RequestMethod.GET)
  public String date(RequestContext request) {
    request.setAttribute("msg", "自定义参数解析器测试");
    return "/param/date";
  }

  @ResponseBody
  @ActionMapping(value = "/user/date", method = RequestMethod.POST)
  public User date(User user) {
    return user;
  }

  @ResponseBody
  @ActionMapping(value = "/user/d", method = RequestMethod.GET)
  public Date date(Date date) {
    return date;
  }

  @ResponseBody
  @ActionMapping(value = "/user/set", method = RequestMethod.POST)
  public Set<User> set(Set<User> users) {
    return users;
  }

}
