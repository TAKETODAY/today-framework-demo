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
package cn.taketoday.demo.service.impl;

import cn.taketoday.context.annotation.Autowired;
import cn.taketoday.context.annotation.Service;
import cn.taketoday.demo.aspect.Logger;
import cn.taketoday.demo.domain.User;
import cn.taketoday.demo.repository.UserRepository;
import cn.taketoday.demo.service.UserService;

/**
 * @author Today <br>
 * 2018-10-27 09:56
 */
@Service
//@CacheConfig(cacheName = "loginUser", expire = 30, timeUnit = TimeUnit.SECONDS)
public class DefaultUserService implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  @Logger("login in service")
//	@Cacheable(key = "userEmail_${user.email}", unless = "empty result", condition = "${!empty user.email}", sync = true)
  public User login(User user) {
    return userRepository.login(user);
  }

  @Override
  public void register(User user) {
    userRepository.save(user);
  }

}
