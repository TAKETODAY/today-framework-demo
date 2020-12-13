package cn.taketoday.demo.controller;

import cn.taketoday.demo.view.Json;
import cn.taketoday.web.RequestMethod;
import cn.taketoday.web.annotation.CrossOrigin;
import cn.taketoday.web.annotation.EnableCrossOrigin;
import cn.taketoday.web.annotation.GET;
import cn.taketoday.web.annotation.PostMapping;
import cn.taketoday.web.annotation.RestController;
import cn.taketoday.web.http.ResponseEntity;

/**
 * @author TODAY
 * @date 2020/12/8 21:35
 */
@RestController
@EnableCrossOrigin
public class ResponseEntityController {

  @PostMapping("/response-entity")
  @GET("/response-entity")
  @CrossOrigin("https://taketoday.cn")
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Json> responseEntity() {
    return ResponseEntity.ok()
            .allow(RequestMethod.POST, RequestMethod.PUT)
            .header("X-test", "test", "test1")
            .body(Json.success());
  }

}
