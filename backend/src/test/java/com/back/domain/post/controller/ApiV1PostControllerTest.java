package com.back.domain.post.controller;

import com.back.domain.post.entity.Post;
import com.back.domain.post.service.PostService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class ApiV1PostControllerTest {

  @Autowired
  private PostService postService;

  @Autowired
  private MockMvc mvc;

  @Test
  @DisplayName("다건 조회")
  void t1() throws Exception {
    // when
    var resultActions = mvc
        .perform(get("/api/v1/posts"))
        .andDo(print());

    List<Post> posts = postService.findAll();

    // then
    resultActions
        .andExpect(handler().handlerType(ApiV1PostController.class))
        .andExpect(handler().methodName("getItems"))
        .andExpect(status().isOk());

    for (int i = 0; i < posts.size(); i++) {
      Post post = posts.get(i);
      resultActions
          .andExpect(jsonPath("$[%d].id".formatted(i)).value(post.getId()))
          .andExpect(jsonPath("$[%d].title".formatted(i)).value(post.getTitle()));
    }
  }
}
