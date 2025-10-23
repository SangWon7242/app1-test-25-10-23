package com.back.global.initData;

import com.back.domain.post.service.PostService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class BaseInitData {
  private final PostService postService;

  @Autowired
  @Lazy
  private BaseInitData self;

  public BaseInitData(PostService postService) {
    this.postService = postService;
  }

  @Bean
  public ApplicationRunner baseInitDataApplicationRunner() {
    return args -> {
      self.work1();  // 프록시를 통해 호출
    };
  }

  @Transactional
  public void work1() {
    if (postService.count() > 0) return;

    postService.write("제목 1", "내용 1");
    postService.write("제목 2", "내용 2");
  }
}
