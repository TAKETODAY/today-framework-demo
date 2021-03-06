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

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cn.taketoday.context.annotation.Autowired;
import cn.taketoday.web.RequestContext;
import cn.taketoday.web.RequestMethod;
import cn.taketoday.web.annotation.Controller;
import cn.taketoday.web.annotation.RequestMapping;
import cn.taketoday.web.ui.ModelAndView;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Today <br>
 *
 * 2018-12-09 22:53
 */
@Slf4j
@Controller("modelController")
@RequestMapping(value = { "model", "and", "view" }, method = RequestMethod.GET)
public class ModelAndViewController {

  @Autowired
  private RequestContext request;

  @RequestMapping
  public ModelAndView model() {
    return new ModelAndView("/model/index", "key", "World").setContentType("text/html;charset=UTF-8");
  }

  @RequestMapping("index")
  public void index(ModelAndView modelAndView) {
    modelAndView.setView(request.requestBody());
    modelAndView.setView(request.getRequestPath());
  }

  @RequestMapping("nothing")
  public ModelAndView nothing() {
    log.info("nothing");
    return new ModelAndView();
  }

  @RequestMapping("/script")
  public void script(ModelAndView modelAndView) {
    modelAndView.setContentType("text/html;charset=UTF-8");
    modelAndView.setView(new StringBuilder("<script>alert('HELLO， 你好');</script>"));
  }

  @RequestMapping("/display")
  public void display(ModelAndView modelAndView) throws IOException {
    modelAndView.setView(ImageIO.read(new File("D:/WebSite/data/doc/upload/logo.png")));
  }
}
