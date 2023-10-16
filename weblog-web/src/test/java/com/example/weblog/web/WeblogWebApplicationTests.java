package com.example.weblog.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Slf4j
@SpringBootTest
class WeblogWebApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void testLoads() {
        log.info("这是一行 Info 级别的日志哦~");
        log.warn("这是一行 Warn 级别日志哦~");
        log.error("这是一行 Error 级别的日志哦~");

        // 占位符
        String author = "阿政a";
        log.info("这是一行带有占位符的日志,作者：{}",author);
    }

}
