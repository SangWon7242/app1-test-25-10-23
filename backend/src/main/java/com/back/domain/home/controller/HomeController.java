package com.back.domain.home.controller;

import com.back.global.rq.Rq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
  private final Rq rq;

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

    return "main(version : 0.0.1), hostname : %s".formatted(localHost.getHostName());
  }

  @GetMapping("/cookie/{name}/{value}")
  @ResponseBody
  public String setCookie(@PathVariable String name, @PathVariable String value) {
    rq.setCookie(name, value);

    return "%s=%s".formatted(name, value);
  }

  @GetMapping("/cookie/{name}")
  @ResponseBody
  public String getCookie(@PathVariable String name) {
    String cookieValue = rq.getCookieValue(name);
    return cookieValue != null ? cookieValue : "";
  }
}
