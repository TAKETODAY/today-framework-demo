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
package cn.taketoday.demo.repository.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.taketoday.demo.domain.User;
import cn.taketoday.demo.repository.UserRepository;

/**
 * @author Today <br>
 * 2018-10-27 09:57
 */
//@Repository
public final class UserRepositoryInMem implements UserRepository {

  private Map<Long, User> users = new HashMap<>();

  public UserRepositoryInMem() {
    users.put(666l, new User().setId(666).setName("杨海健").setPassword("666"));
    users.put(6666l, new User().setId(6666).setName("杨海健1").setPassword("666"));
    users.put(66666l, new User().setId(66666).setName("杨海健2").setPassword("666"));
    users.put(666666l, new User().setId(666666).setName("杨海健3").setPassword("666666"));
  }

  @Override
  public void save(User user) {
    users.put(user.getId(), user);
  }

  @Override
  public User login(User user) {
    if (user == null) {
      return null;
    }
    User user_ = users.get(user.getId());

    if (user_ == null) {
      return null;
    }
    if (!user_.getPassword().equals(user.getPassword())) {
      return null;
    }
    return user_;
  }

  @Override
  public void saveAll(Collection<User> models) {

  }

  @Override
  public void saveSelective(User model) {

  }

  @Override
  public void delete(User model) {

  }

  @Override
  public void deleteAll(Collection<User> models) {

  }

  @Override
  public void deleteById(Long id) {

  }

  @Override
  public void update(User model) {

  }

  @Override
  public void updateAll(Collection<User> models) {

  }

  @Override
  public int getTotalRecord() {
    return 0;
  }

  @Override
  public User findById(Long id) {
    return null;
  }

  @Override
  public List<User> findAll() {
    return null;
  }

  @Override
  public List<User> find(int pageNow, int pageSize) {
    return null;
  }

  @Override
  public void deleteAll() {

  }

  @Override
  public List<User> findLatest() {
    return null;
  }
}
