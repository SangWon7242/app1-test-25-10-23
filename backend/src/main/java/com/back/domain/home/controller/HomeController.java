package com.back.domain.home.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
@Slf4j
public class HomeController {

  @GetMapping("/")
  @ResponseBody
  public String main() {
    // 로컬 호스트 정보를 저장할 변수 초기화
    InetAddress localHost = null;

    try {
      localHost = InetAddress.getLocalHost();
    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    }

    System.out.println("Runs in any environment");
    // log.info("Runs in any environment");
    log.debug("Run in dev/prod environments");

    return "main(version : 1.0.2), hostname : %s".formatted(localHost.getHostName());
  }
}
