package com.back.global;

import com.back.global.app.AppConfig.AppConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    String genFileDirPath = AppConfig.getGenFileDirPath();
    String resourceLocation = Paths.get(genFileDirPath)
        .toUri()
        .toString()
        .replace("file:/", "file:///");

    registry.addResourceHandler("/gen/**")
        .addResourceLocations(resourceLocation);
  }
}
