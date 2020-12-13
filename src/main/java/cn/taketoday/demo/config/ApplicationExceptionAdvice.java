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

import cn.taketoday.demo.view.Json;
import cn.taketoday.web.annotation.ControllerAdvice;
import cn.taketoday.web.annotation.ExceptionHandler;
import cn.taketoday.web.annotation.ResponseStatus;
import cn.taketoday.web.exception.AccessForbiddenException;
import cn.taketoday.web.exception.FileSizeExceededException;
import cn.taketoday.web.exception.NotFoundException;
import cn.taketoday.web.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;

/**
 * @author TODAY <br>
 * 2019-04-20 16:22
 */
@Slf4j
@ControllerAdvice
public class ApplicationExceptionAdvice {

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(UnauthorizedException.class)
  public Json unauthorized() {
    log.error("Unauthorized");
    return Json.unauthorized();
  }

  @ExceptionHandler(AccessForbiddenException.class)
  public Json accessForbidden() {
    log.error("Access Forbidden");
    return Json.badRequest("权限不足");
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({ IllegalArgumentException.class })
  public Json badRequest(IllegalArgumentException illegalArgumentException) {
    log.error("Bad request", illegalArgumentException);
    return Json.badRequest(illegalArgumentException.getMessage());
  }

  @ExceptionHandler({ NotFoundException.class })
  public Json notFound(NotFoundException exceededException) {
    log.error(exceededException.getMessage(), exceededException);

    return Json.notFound();
  }

  @ExceptionHandler({ FileSizeExceededException.class })
  public Json badRequest(FileSizeExceededException exceededException) {
    log.error(exceededException.getMessage(), exceededException);
    return Json.badRequest("上传文件大小超出限制");
  }

  @ExceptionHandler
  public Json error(Throwable exception) {

    log.error("An Exception occurred", exception);

    return Json.failed(exception.getMessage());
  }

}
