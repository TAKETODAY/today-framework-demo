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
package cn.taketoday.demo.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@SuppressWarnings("serial")
public class User implements Serializable {

  /** id register time */
  private long id;
  /** state */
  @JSONField(serialize = false)
  private byte state;
  /** name */
  private String name;
  /** email */
  private String email;
  /** web site */
  private String site;
  /** type */
  @JSONField(serialize = false)
  private String type;
  /** passwd */
  @JSONField(serialize = false)
  private String password;
  /** avatar */
  private String image;
  /** description */
  private String description;
  /** back ground **/
  private String background;

  @Override
  public String toString() {
    return new StringBuilder()//
            .append("{\n\t\"id\":\"").append(id)//
            .append("\",\n\t\"name\":\"").append(name)//
            .append("\",\n\t\"site\":\"").append(site)//
            .append("\",\n\t\"type\":\"").append(type)//
            .append("\",\n\t\"state\":\"").append(state)//
            .append("\",\n\t\"image\":\"").append(image)//
            .append("\",\n\t\"email\":\"").append(email)//
            .append("\",\n\t\"password\":\"").append(password)//
            .append("\",\n\t\"background\":\"").append(background)//
            .append("\",\n\t\"description\":\"").append(description)//
            .append("\"\n}").toString();
  }

}
