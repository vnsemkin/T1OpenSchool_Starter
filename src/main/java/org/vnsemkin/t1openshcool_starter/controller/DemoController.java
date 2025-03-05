package org.vnsemkin.t1openshcool_starter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vnsemkin.t1openshcool_starter.dto.ResultDTO;


@RestController
public class DemoController {

  @GetMapping("/api/v1/hello")
  public ResultDTO hello() {
    return ResultDTO
            .builder()
            .message("Hello World!")
            .build();
  }
}
